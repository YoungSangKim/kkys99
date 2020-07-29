import java.io.*;
import java.util.*;

public class n10952 {

	static int x, y;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			if (x == 0 && y == 0)
				return;
			System.out.println(x + y);
		}
	}
}


import timeit
start = timeit.default_timer()
import findspark
findspark.init()
import pyspark
import pyspark.sql.functions as F
import os
import pandas as pd
from pyspark.conf import SparkConf
from pyspark.sql import DataFrameWriter
from pyspark.sql import SparkSession
from pyspark.context import SparkContext
from pyspark.sql.window import Window
from pyspark.sql import Row
from pyspark.sql.functions import lower, col
from pyspark.sql.types import StructType, StructField, StringType, DoubleType

os.environ["HADOOP_USER_NAME"] ="mdd000"

settings = [
    # Hive Metastore Host 와 Port
    # ("hive.metastore.uris","thrift://aiplatform:9083"),
    ("hive.metastore.uris", f"thrift://ailake:9083"),
    # spark 드라이버 셋팅 configuration
    # Drive Core 수
    ("spark.driver.cores", 4),
    # Drive Core memory 용량
    ("spark.executor.cores",4),
    #E xecutor memory 용량
    ("spark.executor.memory", "16G"),
    # Executor Core 수
    ("spark.executor.cores", 4),
    # Executor memory 용량
    ("spark.executor.memeory", "24G"),
    ("spark.executor.instances",3),
    # ("spark.driver.maxResultSize", "6G"),
    # Metastore ORC FileFormat을 해당 테이블에 기본 Serde로 읽도록 설정
    ("spark.sql.hive.convertMetastoreOrc", "false"),
    ("spark.sql.hive.convertMetastoreParquet", "false"),
    ("spark.sql.execution.arrow.enabled", "true"),
    ("spark.sql.crossJoin.enabled", "true"),
    ("spark.ui.enabled", "false"),
    ("hive.input.dir.recursive", "true"),
    ("hive.mapred.supports.subdirectories", "true"),
    ("hive.supports.subdirectories","true"),
    ("mapreduce.input.fileinputformat.input.dir.recursive","true"),
]

conf = SparkConf().setAll(settings)

# sparkSession 객체 빌드
spark = SparkSession.builder.config(conf=conf).enableHiveSupport().getOrCreate()

DATE = '20200727'

data_type = pd.read_csv('C:/Users/Administrator/SKcc_fin_project_1/PYSPARK/dataType.csv')

tbl_list = ['tetaaco26d'
,'tetaadp01d'
,'tetaadp81d'
,'tetaale05d'
,'tetaale14d'
,'tetaale16d'
,'tetaaleq5d'
,'tetaalesad'
,'tetaalet4d'
,'tetaalew3d'
,'tetaalo01d'
,'tsmdddc01'
,'tsmdddh01'
,'tsmddem02'
,'tsmddmc03_사기'
,'tsmddmc03_정상'
,'tsmddmh03'
,'tsmddmh04'
,'tsmddmh05']

data_dir = 'C:/Users/Administrator/SKcc_fin_project_1/PYSPARK/data_csv/'

df_dict = {}


for tbl in tbl_list:
    df = spark.read.csv(data_dir + tbl +'.csv', header = True,)
    df = df.drop('_c0')
    
    table_schema=data_type[data_type['테이블명']==tbl.upper()]
    
    column_full=table_schema['컬럼명'].tolist()
    column_str=table_schema[table_schema['타입']=='string']['컬럼명'].tolist()
    column_double=table_schema[table_schema['타입']=='double']['컬럼명'].tolist()
    
    for col in column_str:
        df = df.withColumn(col, df[col].cast("string"))
        if col == 'lnbzathoraplcnno':
            df.withColumn('lnbzathoraplcnno',F.trim(F.col('lnbzathoraplcnno')))
        if col == 'exmtncustidnfr':
            df.withColumn('exmtncustidnfr',F.trim(F.col('exmtncustidnfr')))
        if col == 'acno':
            df.withColumn('acno',F.trim(F.col('acno')))
    for col in column_double:
        df = df.withColumn(col, df[col].cast("double"))
    
    df_dict[tbl] = df
    
CO26D = df_dict['tetaaco26d']
DP01D = df_dict['tetaadp01d']
DP81D = df_dict['tetaadp81d']
LE05D = df_dict['tetaale05d']
LE14D = df_dict['tetaale14d']
LE16D = df_dict['tetaale16d']
LEQ5D = df_dict['tetaaleq5d']
LESAD = df_dict['tetaalesad']
LET4D = df_dict['tetaalet4d']
LEW3D = df_dict['tetaalew3d']
LO01D = df_dict['tetaalo01d']
DC01 = df_dict['tsmdddc01']
DH01 = df_dict['tsmdddh01']
EM02 = df_dict['tsmddem02']
#df.dict['tsmddmc03_사기']
#df.dict['tsmddmc03_정상']
#df.dict['tsmddmh03']
#df.dict['tsmddmh04']
#df.dict['tsmddmh05']

### Step 1
#### LE16D & LE05D JOIN

step1 = LE16D.join(
    LE05D,
    on = [
        'lnbzathoraplcnno'
    ],
    how = 'inner'
)

### Step 2
#### step1 & LET4D JOIN

step2 = step1.join(
    LET4D,
    on = [
        'lnbzathoraplcnno'
    ],
    how = 'inner'
)

### Step 3
#### df -> LE14D SELECT
#### step2 & df JOIN

#중복 칼럼 제거

step2 = step2.drop('exmtncustidnfr')

df = LE14D.select(
    '*',
    F.when(
        F.col('labrincmdstcd') == '1',
        F.col('ylabrincmamt')
    ).otherwise(
        F.lit('0')
    ).alias(
        'ylabrincmamt_'
    ),
    F.when(
        F.col('bizincmdstcd') == '1',
        F.col('bizincmamt')
    ).otherwise(
        F.lit('0')
    ).alias(
        'bizincmamt_'
    ),
    F.when(
        F.col('etcincmdocsdstcd') == '1',
        F.col('etcincmamt')
    ).otherwise(
        F.lit('0')
    ).alias(
        'etcincmamt_'
    ),
    F.when(
        F.col('prtaxrelatddocsdstcd') == '1',
        F.col('prtaxpaymtamt')
    ).otherwise(
        F.lit('0')
    ).alias(
        'prtaxpaymtamt_'
    ),
)

step3 = step2.join(
    df,
    on = [
        'lnbzathoraplcnno'
    ],
    how = 'inner'
)

### Step 4
#### df -> LEW3D SELECT
#### step3 & df JOIN

df = LEW3D.groupBy(
    'lnbzathoraplcnno'
).agg(
    F.count('*').alias('lnnoitm'),
    F.sum(
        F.when(
            F.col('mainscurtydstcd') == '000',
            F.lit('1')
        ).otherwise(
            F.lit('0')
        )
    ).alias('scurlnynnoitm'),
    F.sum(
        F.when(
            F.col('limttranagredstcd') == '0',
            F.lit('1')
        ).otherwise(
            F.lit('0')
        )
    ).alias('limtynnoitm'),
    F.sum(
        F.col('lnagreamt')
    ).alias('lntamt'),
)

step4 = step3.join(
    df,
    on = [
        'lnbzathoraplcnno'
    ],
    how = 'left_outer'
)

### Step 5
#### step4 & LEQ5D JOIN

#칼럼명 변경
step4 = step4.withColumnRenamed("exmtncustidnfr", "_exmtncustidnfr")

step5 = step4.join(
    LEQ5D,
    on = 
        (step4['_exmtncustidnfr'] == LEQ5D['exmtncustidnfr']) &
        (step4['kcbinfooferinquryyms'] == LEQ5D['crdtinfoinquryyms'])
    ,
    how = 'left_outer'
)

### Step 6
#### df1 --> LE16D SELECT
#### df2 --> df1 & LESAD JOIN
#### step5 & df2 JOIN

df1 = LE16D.select(
    'lnbzathoraplcnno',
    'lpsprlndocssendyms'
).where(
    F.col('lpsprlndocssendyn') == '1'
)

df2 = df1.join(
    LESAD,
    on = [
        'lnbzathoraplcnno'
    ],
    how = 'inner'
).groupby(
    'lnbzathoraplcnno'
).agg(
    F.sum(
        F.when(
            (F.col('lpsprlndocssendyms') > F.col('lnoccurymd')) &
            ((F.col('kfblndstcd') == '210') | (F.col('kfblndstcd') == '220') |
             (F.col('kfblndstcd') == '230')),
            F.lit('1')
        ).otherwise(
            F.lit('0')
        )
    ).alias(
        'temp3'
    ),
    F.sum(
        F.when(
            (F.col('lpsprlndocssendyms') > F.col('lnoccurymd')) &
            (F.col('fiorgjntbnkcd') != '004'),
            F.lit('1')
        ).otherwise(
            F.lit('0')
        )
    ).alias(
        'temp6'
    )
)

step6 = step5.join(
    df2,
    on = [
        'lnbzathoraplcnno'
    ],
    how = 'left_outer'
)

### Step 7
#### df1 --> LE16D SELECT
#### df2 --> df1 & LESAD JOIN
#### step6 & df2 JOIN

df1 = LE16D.select(
    'lnbzathoraplcnno',
    'lpsprlndocssendyms'
).where(
    F.col('lpsprlndocssendyn') == '1'
)

df2 = df1.join(
    LESAD,
    on = [
        'lnbzathoraplcnno'
    ],
    how = 'inner'
).where(
    (F.col('fiorgjntbnkcd') != '004') & (F.col('lnamt') < 5000000) &
    ((F.col('lnhndlnprdctdstcd') =='01') | (F.col('lnhndlnprdctdstcd') =='12'))
).groupby(
    'lnbzathoraplcnno'
).agg(
    F.max(
        F.when(
            (F.col('lpsprlndocssendyms') > F.col('lnoccurymd')),
            F.col('lnoccurymd')
        ).otherwise(
            F.lit('')
        )
    ).alias(
        'temp4'
    ),
    F.sum(
        F.when(
            (F.months_between(
                F.concat(
                    F.substring('lpsprlndocssendyms',1,4), F.lit('-'),
                    F.substring('lpsprlndocssendyms',5,2),F.lit('-'),
                    F.substring('lpsprlndocssendyms',7,2)
                ),
                F.concat(
                    F.substring('lnoccurymd',1,4), F.lit('-'),
                    F.substring('lnoccurymd',5,2), F.lit('-'),
                    F.substring('lnoccurymd',7,2)
                )
            ) > 0 ) &
            (F.months_between(
                F.concat(
                    F.substring('lpsprlndocssendyms',1,4), F.lit('-'),
                    F.substring('lpsprlndocssendyms',5,2), F.lit('-'),
                    F.substring('lpsprlndocssendyms',7,2)
                ),
                F.concat(
                    F.substring('lnoccurymd',1,4), F.lit('-'),
                    F.substring('lnoccurymd',5,2), F.lit('-'),
                    F.substring('lnoccurymd',7,2)
                )
            ) <= 3 ),
            F.lit('1')
        ).otherwise(
            F.lit('0')
        )
    ).alias(
        'temp5'
    )
)

step7 = step6.join(
    df2,
    on = [
        'lnbzathoraplcnno'
    ],
    how = 'left_outer'
)

### Step8

#### df1 --> LE16D SELECT
#### df2 --> df1 & LE05D JOIN
#### df3 --> df2 & LO01D JOIN
#### Step 7 & df3 JOIN

df1 = LE16D.select(
    'lnbzathoraplcnno',
    'lpsprlndocssendyms'
).where(
    F.col('lpsprlndocssendyn') == '1'
)

df2 = df1.join(
    LE05D,
    on = [
        'lnbzathoraplcnno'
    ],
    how = 'left_outer'
).select(
    'lnbzathoraplcnno',
    'lpsprlndocssendyms',
    'exmtncustidnfr'
)

df3 = df2.join(
    LO01D,
    on = [
       df2['exmtncustidnfr'] == LO01D['ctcrcustidnfr']
    ],
    how = 'inner'
).groupby(
    'lnbzathoraplcnno'
).agg(
    F.sum(
        F.when(F.col('lpsprlndocssendyms') > F.col('prdctctrcagreymd'),
               F.lit('1')
        ).otherwise(
            F.lit('0')
        )
    ).alias(
        'temp7'
    )
)

step8 = step7.join(
    df3,
    on =[
        'lnbzathoraplcnno'
    ],
    how = 'left_outer'
)

### STEP9

#### df1 --> LE16D SELECT
#### df2 --> df1 & LE05D JOIN
#### df3 --> df2 & DP01D JOIN
#### df4 --> df2 & df3 JOIN
#### STEP 8 & df4 JOIN

df1 = LE16D.select(
    'lnbzathoraplcnno',
    'lpsprlndocssendyms'
).where(
    F.col('lpsprlndocssendyn') == '1'
)

df2 = df1.join(
    LE05D,
    on = [
        'lnbzathoraplcnno'
    ],
    how = 'left_outer'
).select(
    'lnbzathoraplcnno',
    'lpsprlndocssendyms',
    'exmtncustidnfr'
)

df3 = CO26D.join(
    DP01D,
    on = [
        'acno'
    ],
    how = 'inner'
).select(
    'ctcrcustidnfr',
    'regiymd',
    'acidntrprtsvrregidstcd'
).where(
    (F.col('acidntrprtsvrregidstcd') == '401') | (F.col('acidntrprtsvrregidstcd') == '402') |
    (F.col('acidntrprtsvrregidstcd') == '403') | (F.col('acidntrprtsvrregidstcd') == '627') |
    (F.col('acidntrprtsvrregidstcd') == '628') | (F.col('acidntrprtsvrregidstcd') == '629') |
    (F.col('acidntrprtsvrregidstcd') == '630') | (F.col('acidntrprtsvrregidstcd') == '631') |
    (F.col('acidntrprtsvrregidstcd') == '632') | (F.col('acidntrprtsvrregidstcd') == '633') |
    (F.col('acidntrprtsvrregidstcd') == '634') | (F.col('acidntrprtsvrregidstcd') == '635') |
    (F.col('acidntrprtsvrregidstcd') == '641') | (F.col('acidntrprtsvrregidstcd') == '642')
)

df4 = df2.join(
    df3,
    on = [
        df2['exmtncustidnfr'] == df3['ctcrcustidnfr']
    ],
    how = 'inner'
).where(
    (F.months_between(
                F.concat(
                    F.substring('lpsprlndocssendyms',1,4), F.lit('-'),
                    F.substring('lpsprlndocssendyms',5,2),F.lit('-'),
                    F.substring('lpsprlndocssendyms',7,2)
                ),
                F.concat(
                    F.substring('regiymd',1,4), F.lit('-'),
                    F.substring('regiymd',5,2), F.lit('-'),
                    F.substring('regiymd',7,2)
                )
            ) > 0 ) &
   (F.months_between(
                F.concat(
                    F.substring('lpsprlndocssendyms',1,4), F.lit('-'),
                    F.substring('lpsprlndocssendyms',5,2),F.lit('-'),
                    F.substring('lpsprlndocssendyms',7,2)
                ),
                F.concat(
                    F.substring('regiymd',1,4), F.lit('-'),
                    F.substring('regiymd',5,2), F.lit('-'),
                    F.substring('regiymd',7,2)
                )
            ) <= 24 )
).groupby(
    'lnbzathoraplcnno'
).agg(
    F.count('*').alias('temp8')
)

step9 = step8.join(
    df4,
    on = [
        'lnbzathoraplcnno'
    ],
    how = 'left_outer'
)

### STEP10

#### df --> DP81D SELECT
#### STEP 9 & df JOIN

df = DP81D.groupBy(
    'custidnfr'
).agg(
    F.min(
        F.col('regiymd')
    ).alias(
        'regiymd'
    )
)

step10 = step9.join(
    df,
    on = [
        (df['custidnfr'] == step9['exmtncustidnfr'])
    ],
    how = 'left_outer'
)

### ResultDF

#### step10 SELECT

resultDF = step10.select(
    F.lit(DATE).alias('baseymd'),
    'lnbzathoraplcnno',
    'exmtncustidnfr',
    'ppsncrtdscd',
    'emrgimdtlnyn',
    'gndrdstcd',
    'wedgdstcd',
    F.when(
        (F.col('curntwplcclsficd') == '060108') | (F.col('curntwplcclsficd') == '060201') |
        (F.col('curntwplcclsficd') == '060107') | (F.col('curntwplcclsficd') == '060199') |
        (F.col('curntwplcclsficd') == '090101') | (F.col('curntwplcclsficd') == '090504') |
        (F.col('curntwplcclsficd') == '100101'),
        F.lit('1')
    ).otherwise(
        F.lit('0')
    ).alias(
        'curntwplcclsficdyn'
    ),
    F.substring('curntwplcclsficd', 1, 2),
    'curntwplcwrknomn',
    F.round((F.col('agecnt') - F.col('curntwplcwrknomn')/12),0).alias('curntwplcentcoage'),
    F.col('lpsprlndocssendyms').alias('lndocssendyms'),
    F.substring('lpsprlndocssendyms', 5, 2).alias('lndocssendm'),
    'curntwpnm',
    'agecnt',
    'owbnktrantrmnomn',
    'niceesttincmamt',
    'salrytrsaccarsitdstcd',
    'nicecbscorescor',
    'lsmnlnbzacncnt',
    'la12mnbsznlnholdexprinsticnt',
    'la6mlnbzholdnotms',
    'la6mowbnklnholdexprnoday',
    'la6mowbnklnholdexprnomn',
    'la6mtucomcnt',
    'la6mbtndrmttotalovrducnt',
    'la6mlnbzovrdunotms',
    F.when(
        F.col('la6mowbnklnlnovnoday') == '9999',
        F.lit('0')
    ).otherwise(
        F.col('la6mowbnklnlnovnoday')
    ).alias(
        'la6mowbnklnlnovnoday'
    ),
    'la6mlnbztotalovrdunoday',
    'la6mlnbzhgstovrdunoday',
    F.when(
        F.col('la6mowbnklnovrdunoacc') == 999,
        F.lit('0')
    ).otherwise(
        F.col('la6mowbnklnovrdunoacc')
    ).alias(
        'la6mowbnklnovrdunoacc'
    ),
    F.when(
        F.col('la6mowbnklnovrdumocnt') == 9999,
        F.lit('0')
    ).otherwise(
        F.col('la6mowbnklnovrdumocnt')
    ).alias(
        'la6mowbnklnovrdumocnt'
    ),
    'la6mindstydpostavbal',
    'la3mtotaldpstavbal',
    'la6mtotaldpstavbal',
    'la6mondmdavbal',
    'la6mlnbzavbal',
    'past6mnthsinsdfmntavbal',
    'past6mnthsondmdavbal',
    'la6mlnbzhgstbal',
    'la3mbtncadvtotalamus',
    'kbcd6mnthstotalpyaccamt',
    'kbcd6mnthslspamtsum',
    'totalcrdlonaplyamt',
    'la6mondmdmrvamt',
    'la3mavgsalrytrsaccamt',
    'la6mavgsalrytrsaccamt',
    'kbecludkcblntotalamt',
    (F.col('ylabrincmamt_')+F.col('bizincmamt_')+F.col('prtaxpaymtamt_')
    ).alias(
        'yrincmamt'
    ),
    F.when(
        F.col('lnnoitm').isNull(),
        F.lit('0')
    ).otherwise(
        F.col('lnnoitm')
    ).alias(
        'lnnoitm'
    ),
    F.when(
        F.col('scurlnynnoitm').isNull(),
        F.lit('0')
    ).otherwise(
        F.col('scurlnynnoitm')
    ).alias(
        'mainscurlnnoitm'
    ),
    F.when(
        F.col('limtynnoitm').isNull(),
        F.lit('0')
    ).otherwise(
        F.col('limtynnoitm')
    ).alias(
        'limttranagrenoitm'
    ),
    F.when(
        F.col('lntamt').isNull(),
        F.lit('0')
    ).otherwise(
        F.col('lntamt')
    ).alias(
        'lntagretamt'
    ),
    'la12mcphnnoflxblnoitm',
    'la12mwtinowhusaddrpshistnoitm',
    'la12mwtinwplcnamepshistnoitm',
    F.when(
        ((F.col('curntwplcclsficd') == '060108') | (F.col('curntwplcclsficd') == '060201')) &
        ((F.col('labrincmdstcd') == '1') | (F.col('wplcrelatddocsdstcd') == '1')),
        F.lit('1')
    ).otherwise(
        F.lit('0')
    ).alias(
        'mandanchkuptagetyn'
    ),
    F.when(
        F.col('temp3').isNull(),
        F.lit('0')
    ).otherwise(
        F.col('temp3')
    ).alias(
        'pastscurtylnholdcnt'
    ),
    F.when(
        F.col('temp5').isNull(),
        F.lit('0')
    ).otherwise(
        F.col('temp5')
    ).alias(
        'n3otrbnksmamtcrdlonoccurcnt'
    ),
    F.when(
        F.col('temp6').isNull(),
        F.lit('0')
    ).otherwise(
        F.col('temp6')
    ).alias(
        'pastotrbnklnbzacnholdcnt'
    ),
    F.when(
        F.col('temp7').isNull(),
        F.lit('0')
    ).otherwise(
        F.col('temp7')
    ).alias(
        'pastowbnklnbzacnholdcnt'
    ),
    F.when(
        F.col('temp8').isNull(),
        F.lit('0')
    ).otherwise(
        F.col('temp8')
    ).alias(
        'n24ondmdacidntrprtpshist'
    ),
    F.when(
        (F.col('custidnfr').isNotNull()) & (F.col('regiymd') <= F.col('lpsprlndocssendyms')),
        F.lit('1')
    ).otherwise(
        F.lit('0')
    ).alias(
        'rtirepensnholdyn'
    ),
    (
        (F.col('ylabrincmamt_') + F.col('bizincmamt_') +
         F.col('etcincmamt_') + F.col('prtaxpaymtamt_')) / F.col('agecnt')
    ).alias(
        'agecntstincmamt'
    ),
    'lgdrt',
    'ppsnlnbzwkdtaildstcd',
    'lnhopeamt',
    'grdperdshnrt',
    'riskscore',
    'apctriskgrddstcd',
    'lnbzaplcnamt',
    'anticizirt',
    'kbecptnlnottermippsnlntotalamt',
    'kbewholbsznnottermicrdlonamhl',
    'kbecludprsnttotalcrdlonamt',
    'kscoregrdctnt',
    'kscorevalscr',
    'la12mkewbsznlnholdexprinsticnt',
    'la12mkewcrdlonrpaypriintamtamt',
    'la12mcsaleutznamt',
    'la12mchkcardutznamt',
    'la12mcbsznlnrpaypriintamtamt',
    'la12mcoenbnkbsznlnopennoitm',
    'la12mcoenienbnkoccuopennoitm',
    'la12mcoenblnrpaypriintamtamt',
    'la12mcoenbcrpaypriintamtamt',
    'la3mcsalezutznamt',
    'la3mchkcardutznamt',
    'la6metcocculnopennoitm',
    'la6mcsalezutznamt',
    'la6mbkenottermippsnlntotalamt',
    'la6mbnbnottermippsnlntotalamt',
    'la6mchkcardutznamt',
    'la6mcoenbnkbsznlnrpaynoitm',
    'rscoregrdctnt',
    'rscorevalscr',
    'lntotalamt',
    'riskscor',
    'nottermicrecrdopeninsticnt',
    'nbnkbsznnottermippsnlnamt',
    'kcbcbsbscrpfctrresndstcd1',
    'kcbcrdtriskgrdcd',
    'lsmnendbase5mnthsbfcbgrdcd',
    'curntoinfofrstregiddelapsnoday',
    'curntwinfofrstregiddelapsnoday',
    'csmriskscoregrdcnt',
    'kbektlnamtalsoretimcrdlonamt',
    'nice1stlnamt',
    'nice2ndlnamt',
    'la12mtotaldpstavbal',
    'la3mondmdavbal'
)

stop = timeit.default_timer()
print(stop-start)
