데이터전처리 - 쓸모없는 값을 실행전 수정하는 것(NaN)

df = pd.DataFrame({'A' : [1,2,np.nan,4,5],
                   'B' : [6,7,8,np.nan,10],
                   'C' : [11,12,13,np.nan,np.nan]})
df

	A	B	C
0	1.0	6.0	11.0
1	2.0	7.0	12.0
2	NaN	8.0	13.0
3	4.0	NaN	NaN
4	5.0	10.0	NaN

pd.isna(df) # nan값을 true

	A	B	C
0	False	False	False
1	False	False	False
2	True	False	False
3	False	True	True
4	False	False	True

pd.isna(df).sum() # nan의 합을 출력

A    1
B    1
C    2

df_drop_nan = df.dropna() # nan값이 있는 열을 지움
df_drop_nan

	A	B	C
0	1.0	6.0	11.0
1	2.0	7.0	12.0

df_drop_B_C = df.dropna(subset=['B','C']) # B와 C의 nan값을 제거거
df_drop_B_C

A	B	C
0	1.0	6.0	11.0
1	2.0	7.0	12.0
2	NaN	8.0	13.0

pd.isna(df)

df_0 = df['C'].fillna(0) # C행의 결측치를 0으로 바꿈
print(df_0)

df_missing = df['A'].fillna('missing') # A행의 결측치를 missing으로 바꿈
df_missing

#df.fillna(df.mean(), inplace = True)
df_mean = df.fillna(df.mean()) # 결측치를 평균으로 바꿈
print(df,'\n')
print(df_mean)

print(df,'\n')
df_fill = df.fillna(method = 'ffill') # NAN값을 전 값으로 채움
print(df_fill,'\n')

df_bfill = df.fillna(method = 'bfill') # NAN값을 다음값으로 채움
print(df_bfill)

fill_dict = {'A':df['A'].mean(),'B':'12/25','C':'missing'} # NAN값을 A의 평균,12/25,MISSING으로 채움 
df_filled= df.fillna(fill_dict)

	A	B	C
0	1.0	6.0	11.0
1	2.0	7.0	12.0
2	3.0	8.0	13.0
3	4.0	12/25	missing
4	5.0	10.0	missing

이상치를 제거하는것 박스플롯,IQR(분위수Q1 - 25, Q2 - 50, Q1- 1.5 * IQR = 최솟값, Q3 +1.5 * IQR = 최댓값


#os.getcwd()
#os.mkdir('test테스트')
#os.chdir('test테스트')
#os.getcwd()
#with open("test.csv","w") as f:
 #   f.write("aaaa,b,c,d,message \n")
  #  f.write("aaa1,2,3,4,hello \n")
#with open("test.csv","a") as f:

    
 #   f.write("aaaa,b,c,d,message \n")
  #  f.write("aaa1,2,3,4,hello \n")
#os.listdir()
#os.rename('test.csv','new_one')
#os.listdir()
#os.remove('new_one')
#os.listdir()
#os.chdir('..')
#os.getcwd()
import os
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import warnings
from datetime import *
warnings.filterwarnings('ignore')

path = '/파일이름/' # 
file_list = os.listdir(path) # 탐색기 
file_list
['대전태평(암반)_2009_hr.xlsx',
 '대전태평(암반)_2010_hr.xlsx',
 '대전태평(암반)_2011_hr.xlsx',
 '대전태평(암반)_2012_hr.xlsx',
 '대전태평(암반)_2013_hr.xlsx',
 '대전태평(암반)_2014_hr.xlsx',
 '대전태평(암반)_2015_hr.xlsx',
 '대전태평(암반)_2016_hr.xlsx']
file_list_py = [file for file in file_list if file.endswith('.xlsx')] # 엑셀 파일만 확인
file_list_py

file_list_py = [file for file in file_list if file.endswith('.xlsx')]
file_list_py

df = pd.DataFrame() # 공백 프레임 만듬
for i in file_list_py: # 
    data = pd.read_excel(path + i) # 한개씩 데이터를 읽음
    df = pd.concat([df,data]) # 빈공백에 한개씩 읽은 데이터 concat함 (이어붙힘)
df

관측소	구분	날짜	시간	수온(℃)	수위(el.m)	EC(㎲/㎝)
0	대전태평	(암반)	20090101	0	16.1	44.47	749
1	대전태평	(암반)	20090101	1	16.1	44.47	749
2	대전태평	(암반)	20090101	2	16.1	44.47	749
3	대전태평	(암반)	20090101	3	16.1	44.47	749
4	대전태평	(암반)	20090101	4	16.1	44.47	749
...	...	...	...	...	...	...	...
8761	대전태평	(암반)	20161231	19	16.2	44.49	725
8762	대전태평	(암반)	20161231	20	16.2	44.49	725
8763	대전태평	(암반)	20161231	21	16.2	44.49	725
8764	대전태평	(암반)	20161231	22	16.2	44.49	725
8765	대전태평	(암반)	20161231	23	16.2	44.49	725

df.head(3) # 데이터 앞에 3개만 보여줌

df.tail() # 데이터 뒤에 5개만 보여줌

df = df.reset_index(drop = True) ;  df # 기존 인덱스를 열로 바꿔라 

df.info() # 어떤 값들로 이루어져있는지에 대한 정보

# '날짜' 열을 'YYYYMMDD' 형식의 문자열에서 datetime 객체로 변환하고,
# '시간' 열을 정수형 시간 값으로 변환한 후, 이를 시간 단위의 timedelta로 변환하여 더함
# 결과적으로 '날짜'와 '시간'을 결합하여 새로운 'Data' 열을 생성함
df['Data'] = pd.to_datetime(df['날짜'], format='%Y%m%d') + \
             pd.to_timedelta(df['시간'].astype(int), unit='h')

# 생성한 'Data' 열을 데이터프레임의 인덱스로 설정함
# inplace=True로 설정하여 데이터프레임을 직접 수정함
df.set_index(df['Data'], inplace=True)

# 데이터프레임의 처음 3개 행을 출력함
df.head(3)

	관측소	구분	날짜	시간	수온(℃)	수위(el.m)	EC(㎲/㎝)	Data
Data								
2009-01-01 00:00:00	대전태평	(암반)	20090101	0	16.1	44.47	749	2009-01-01 00:00:00
2009-01-01 01:00:00	대전태평	(암반)	20090101	1	16.1	44.47	749	2009-01-01 01:00:00
2009-01-01 02:00:00	대전태평	(암반)	20090101	2	16.1	44.47	749	2009-01-01 02:00:00

df1 = df[['수온(℃)','수위(el.m)','EC(㎲/㎝)']]
df1.columns = ['temp','level','EC'] # 칼럼명 변경
df1.head(3) 

df1.isnull().sum() # null개수 구함

df1.to_csv('./파일이름/대전지하수.csv',encoding='cp949') # 데이터폴더는 cp949고정 대전 지하수.csv파일을 저장하기

df = pd.read_csv('./염쨩/대전지하수.csv',index_col = 'Data',
                 parse_data=True,encoding = '949')
df.head(4)

	temp	level	EC
Date			
2009-01-01 00:00:00	16.1	44.47	749
2009-01-01 01:00:00	16.1	44.47	749
2009-01-01 02:00:00	16.1	44.47	749
2009-01-01 03:00:00	16.1	44.47	749



plt.subplot(1,3,1); df.boxplot(column = 'temp',return_type='both')
plt.subplot(1,3,2); df.boxplot(column = 'temp',return_type='both')
plt.subplot(1,3,3); df.boxplot(column = 'temp',return_type='both')
plt.show()

#IQR에 1.5배 멀어진 값은 이상치로 간주
# 데이터 전처리 사용이유 - 쓸모없는 데이터 처리

df.describe() #통계값 구하기.

temp	level	EC
count	69548.000000	69548.000000	69548.000000
mean	16.262491	44.295353	727.024027
std	0.089778	2.539464	51.544590
min	15.800000	11.530000	13.000000
25%	16.200000	44.370000	722.000000
50%	16.300000	44.460000	730.000000
75%	16.300000	44.550000	742.000000
max	16.500000	45.900000	778.000000

df.hist(bins=50,figsize=(10,6)) #구간은 50 그래프 크기는 10,6으로
plt.show()

df.boxplot() # 전체의 박스플롯을 표현.
plt.show()

df.boxplot(column='EC') #EC의 박스플롯만 표현.
plt.show()

plt.subplot(1,3,1);df.boxplot(columns='temp',return_type='both')
plt.subplot(1,3,2);df.boxplot(columns='level',return_type='both')
plt.subplot(1,3,3);df.boxplot(columns='EC',return_type='both')

q3_level=df['level'].quantile(q=0.75)
print(q3_level)

q1_level=df['level'].quantile(q=0.25)
print(q1_level)

iqr_level=q3_level - q1_level
print(iqr_level)

        결과
44.55
44.37
0.17999999999999972

upper_level = q3_level + 1.5 * iqr_level
lower_level = q1_level - 1.5 * iqr_level

print(upper_level,'/',lower_level)
print((df['level'] > upper_level).sum())
print((df['level'] < lower_level).sum())

           결과
44.81999999999999 / 44.099999999999994
4492
1273

df_iqr_level = df[(df['level'] < upper_level) & df['level']>lower_level]
df_iqr_level.head(3)

df_iqr_level.describe()
    결과
	temp	level	EC
count	0.0	0.0	0.0
mean	NaN	NaN	NaN
std	NaN	NaN	NaN
min	NaN	NaN	NaN
25%	NaN	NaN	NaN
50%	NaN	NaN	NaN
75%	NaN	NaN	NaN
max	NaN	NaN	NaN

df_iqr_level['level'].plot()
plt.show()

iqr_level.boxplot(columns='level')
boxplot.show()

2.1 탐색적 분석 정의
분석할 데이터의 전체적인 특성을 살펴보는것 데이터 탐색
데이터 탐색에 데이터를 시각화하여 그래프를 그려보는 방법이 기본
히트소토그램,박스플롯
범주형
1 편의상 문자를 숫자로 대체하는것 2 ex) 요일을 월화수 -> 1 2 3

순서형
1 여성의 옷 사이즈를 나타내는 44 55 66같은 것들

정형과 비정형 데이터
1 정형화된 데이터로 바꿔줘야함

import platform

from matplotlib import font_manager, rc 

import matplotlib

# '-' 부호가 제대로 표시되게 하는 설정 

matplotlib.rcParams['axes.unicode_minus'] = False

# 운영 체제마다 한글이 보이게 하는 설정 # 윈도우

if platform.system() == 'Windows':

    path = "c:\Windows\Fonts\malgun.ttf"

    font_name = font_manager.FontProperties(fname=path).get_name() 

    rc('font', family=font_name) 

elif platform.system() == 'Darwin':  #맥

    rc('font', family='AppleGothic')

elif platform.system() == 'Linux': # 리눅스

    rc('font', family='NanumBarunGothic')

from matplotlib import pyplot as plt
%matplotlib inline

power_data = pd.read_excel('염쨩/시도별_용도별.xls')
print(power_data.shape)

power_data.head(5)

power_data.columns

power_data.values # 제목들만보여줌

power_data2 = pd.read_excel('염쨩/시도별_용도별.xls',header = 2) # 2라인까지다
print(power_data2.shape)
power_data2.head(5)

power= power.drop(['개성','합계'],errors='ignore') # power데이터에서 개성 함계를 지워라
power

power.info()

power.describe()

power.count() # 파워의 행의 개수를 보여줌

corr()
두 변수간의 관계의 강도를 상관관계
그 정도를 파악하는 것을 상관 개수
1에 가까울수록 변수 간에 양의 상관관계를 가지며
-1에 가까울수록 음의 상관관계를 가진다

power.corr()

power = power[['서비스업','제조업']]
power.head(3)

power = power.drop(['경기','서울'])

plt.figure(figsize = (8,8))
plt.scatter(power['서비스업'],power['제조업'],c='k',marker='o')
plt.xlabel('서비스업')
plt.ylabel('제조업')

for n in range(power.shape[0]):
    plt.text(power['서비스업'][n]*1.03, power['제조업'][n]*0.98,power.index[n])

import seaborn as sns
from scipy.stats import norm 
from sklearn.preprocessing import *
from scipy import stats
import warnings
warnings.filterwarnings('ignore')
%matplotlib inline

df_train = pd.read_csv('./염쨩/train.csv')
df_train.columns[:10]
df_train.shape

df_train['SalePrice'].describe()

sns.distplot(df_train['SalePrice'])

var = 'GrLivArea'
#var = input('알고싶은 지역명')
data = pd.concat([df_train['SalePrice'],df_train[var]],axis=1)
data

data.plot.scatter(x=var,y='SalePrice',ylim=(0,1000000));

corrmat = df_train.corr()
f, ax = plt.subplots(figsize = (12,9))
sns.heatmap(corrmat,vmax =.8,square=False)

total = df_train.isnull().sum().sort_values(ascending=False) # null들의 합을 내림차순 정렬
df_train.isnull().mean().sort_values(ascending=False) # 넏들의 평균의 내림차순

percent = (df_train.isnull().sum() 
           /df_train.isnull().count()).sort_values(ascending=False)

missing_data=pd.concat([total,percent],axis = 1,keys=['Total','Percent'])
missing_data.head(20)

df_train = df_train.drop((missing_data[missing_data['Total']>1]).index,1)
df_train = df_train.drop(df_train.loc[df_train['Electrical'].isnull()].index)
print(df_train.isnull().sum().max())
df_train

df_train['SalePrice']

saleprice_scaled = StandardScaler().fit_transform(df_train['SalePrice'][:,np.newaxis]); # 
low_range = saleprice_scaled[saleprice_scaled[:,0].argsort()][:10]
high_range = saleprice_scaled[saleprice_scaled[:,0].argsort()][-10:]
print('outer range (low) of the distribution:')
print(low_range)
print('outer range (high) of the distribution:')
print(high_range)

df = pd.DataFrame({
    'x1' : np.random.normal(0,2,10000), # (평균,표준편차,개수) 정규분포 확률밀도에 의한 무작위값을 출력
    'x2' : np.random.normal(5,3,10000),
    'x3' : np.random.normal(-5,5,10000)
})
print(df)
df.plot.kde()

from sklearn.preprocessing import StandardScaler # import뒤에 임의의 값 이름 지정

standardscaler = StandardScaler() 
data_tf = standardscaler.fit_transform(df)
df = pd.DataFrame(data_tf,columns =['x1','x2','x3'])

df.plot.kde() # 커널 밀더 주정(KDE : Kernel Densiry Estimator)

# 파이썬에서 파일이 해주는것 뉴클리드거리

D.S- D-A =>설명을 위해서

Data -> 수집.측정.웹크롤링
전처리 ->nan.틀린.이상치-> 삭제,대체 ->EDA->describe->boxplot
ML AL ->AI

#상관관계 분석 - > 데이터를 데이터 프레임으로 만들어줘야함!

최대적합 -> test data all 고려 -> AL 복잡, newdata

피어슨 상관분석 -> 가장 일반적
스피어만 상관분석 -> 정규화가 안되어있을때
캔달 상관분석 -> 표본이 적고, 동점이 많을때 사용

# y = 2x+80 == y = ax + b x는 원인,독립변수 y는 결과 -> 종속된다 / 
# 다중 선형 회귀분석(ex- x = 나이,몸무게, y = 혈압),단순 선형 회귀분석

# 선형회귀 예시
fit = ols('y~x',data=df).fit()
print(fit.summary())
