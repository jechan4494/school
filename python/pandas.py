#pandas -> 표 <- dictionary
# 1. 시리즈 : 1차원 
# 2. 데이터프레임 : 2차원 
# Dic key 이름이 -> ㅇ

import numpy as np
import pandas as pd
from numpy import *
from pandas import *

a =pd.Series([1,2,3,4])
print(a)

# 0    1
# 1    2
# 2    3
# 3    4
# dtype: int64

b=pd.Series([1,2,3],index=['a','b','c'])
print(b)

# a    1
# b    2
# c    3
# dtype: int64

region1 = pd.Series(['서울','부산','대구','대전','광주'])
region1

# 0    서울
# 1    부산
# 2    대구
# 3    대전
# 4    광주
# dtype: object

region2=pd.Series(['서울','부산','대구','대전','광주'],index=['a','b','c','d','500'])
region2

# a      서울
# b      부산
# c      대구
# d      대전
# 500    광주
# dtype: object

import pandas as pd
dic3 = {'city' : ['서울'], 'year':[2018]}
data3 =pd.Series(dic3); data3


# city      [서울]
# year    [2018]
# dtype: object

dic4 = {'city' : ['서울'], 'year':[2018]}
data4 =pd.DataFrame(dic4); data4

#	city	year
# 0	서울	2018

dic5=dic3 = {'city' :['서울','부산','대구','대전','광주'],
            'year' : [2017,2017,2018,2018,2018],
            'temp' :[18,20,19,21,20]}
data6 =pd.Series(dic5); data6

# city              [서울, 부산, 대구, 대전, 광주]
# year    [2017, 2017, 2018, 2018, 2018]
# temp              [18, 20, 19, 21, 20]


data5 = pd.DataFrame(dic5)

data5['city']
 =
data5.city # 객체지향 city열만 출력

data5.set_index('city')
data5

#	city	year	temp
# 0	서울	2017	18
# 1	부산	2017	20
# 2	대구	2018	19
# 3	대전	2018	21
# 4	광주	2018	20

data5.index=['가','a',5.2,'라','기린'] ; # data5 #index이름을 바꿔라 

data5.columns=['도시','연도','날씨'] ; # data5 # 도시 연도 날씨로 바꿔라


#	도시	연도	날씨
# 가	서울	2017	18
# a	부산	2017	20
# 5.2	대구	2018	19
# 라	대전	2018	21
# 기린	광주	2018	20

#data['city'] # 도시로 바뀌었기때문에 출력 불가

data5['도시'] # 바꾼값 출력 가능

data5[['도시','날씨']]

data5.도시

data5.loc['기린'] #index 이름으로 접근한다 .loc

# 도시      광주
# 연도    2018
# 날씨      20

data5.set_index('도시') # set_index원본을 바꾸진않고 바꾼 상태를 일시적으로 보여줌(미리보기) 
data5

data8=data5.set_index('도시'); data8 # 도시에 있는 값들을 index로 설정하는것을 data8에 저장
data5.set_index('도시',inplace=True); data5 #inplace=True 원본을 바꿔줌(실제 바꿈)

data5.loc['대전']

data5.iloc[1:4] #1행에서3행까지 출력 

#	도시	연도	날씨
# a	부산	2017	20
# 5.2	대구	2018	190
# 라	대전	2018	21

cars =[50,40,20,30,10]
data5['car'] = cars #car데이터로 추가해라
data5

#	도시	연도	날씨	car
# 가	서울	2017	18	50
# a	부산	2017	20	40
# 5.2	대구	2018	19	20
# 라	대전	2018	21	30
# 기린	광주	2018	20	10

data5['hight1'] = data5.car >=30 # hight1를 추가해되 30보다 클 시 true아니면 false
data5

#	도시	연도	날씨	car	hight1
# 가	서울	2017	18	50	True
# a	부산	2017	20	40	True
# 5.2	대구	2018	19	20	False
# 라	대전	2018	21	30	True
# 기린	광주	2018	20	10	False


data5.drop('hight1',axis=1) # 열단위로 삭제해라(실제 삭제안됨) drop, axis=0(에러), axis x (에러)
data5 

a=data5.drop('hight2',axis=1) # 삭제하는 방식을 a에 저장

data5.drop('hight1',axis=1,inplace=True) #실제로 삭제해라 inplace=True
data5

#	도시	연도	날씨	car	hight2
# 가	서울	2017	18	50	True
# a	부산	2017	20	40	True
# 5.2	대구	2018	19	20	False
# 라	대전	2018	21	30	False
# 기린	광주	2018	20	10	False

data5.drop('대구',axis=0,inplace=True) # 대구 idex 행이 사라짐
data5

#################################################################

import pandas as pd
import numpy as np

# pandas 및 numpy 라이브러리 임포트
b = pd.Series([1, 2, 3], index=['a', 'b', 'c'])  # 시리즈 생성
print(b)


# 행에서 최대값 - 최솟값
def f1(x):
    k = x.max() - x.min()
    return k
    
def f2(x):
    return x.max() - x.min()

f3 = lambda x: x.max() - x.min()  # f3 : 함수 이름 , x : 매개변수 , x.max() - x.min():함수내용
#f1 == f2 == f3

# 데이터프레임 생성
df = pd.DataFrame(np.arange(12).reshape(4, 3),  # arange() 1차원으로 만들어라 reshape() 2차원 배열의 형태를 바꿔라 4행3열
                  columns=['A열', 'B열', 'C열'],
                  index=['a', 'b', 'c', 'd'])
df

#	A열	B열	C열
# a	0	1	2
# b	3	4	5
# c	6	7	8
# d	9	10	11

# 함수를 데이터프레임에 적용
df.apply(f2)  # 열(기본 동작)을 기준으로 f2 함수 적용

df.apply(f3, 0)  # 행을 기준으로 f3 함수 적용

df.apply(f3, 1)  # 열을 기준으로 f3 함수 적용

# a    2
# b    2
# c    2
# d    2

# 다른 데이터프레임 생성
frame = pd.DataFrame(np.arange(8).reshape(2, 4),
                     index=['three', 'one'],
                     columns=['d', 'a', '가', 'c'])

#	d	a	가	c
# three	0	1	2	3
# one	4	5	6	7

# 데이터프레임 정렬
frame.sort_index()  # 인덱스를 기준으로 오름차순 정렬


#	가	d	c	a
# three	2	0	3	1
# one	6	4	7	5

b = frame.sort_index(axis=0)  # 행(인덱스)을 기준으로 오름차순 정렬
b
#	가	d	c	a
# one	6	4	7	5
# three	2	0	3	1
a= frame.sort_index(axis=1)  # 열 이름을 기준으로 오름차순 정렬
a

#	a	c	d	가
# three	1	3	0	2
# one	5	7	4	6

frame.sort_index(axis=1, ascending=False)  # 열 이름을 기준으로 내림차순 정렬
#	가	d	c	a
# three	2	0	3	1
# one	6	4	7	5
frame.sort_index(axis=1, ascending=False, inplace=True)  # 열 이름을 기준으로 내림차순으로 정렬하고 기존 데이터프레임을 변경

# 값에 따라 데이터프레임 정렬
frame.sort_values(by='가', ascending=False)  # '가' 열의 값에 따라 내림차순으로 정렬

#	가	d	c	a
# one	6	4	7	5
# three	2	0	3	1

# 다른 데이터프레임 생성
frame2 = pd.DataFrame({'b': [4, 7, 3, 2], 'a': [4, 9, 2, 5], 'c': [5, 3, 7, 9]})

# 'b' 열을 기준으로 정렬
frame2.sort_values(by='b')

#	b	a	c
# 3	2	5	9
# 2	3	2	7
# 0	4	4	5
# 1	7	9	3

# 'a' 열을 기준으로 정렬
frame2.sort_values(by='a')

#	b	a	c
# 2	3	2	7
# 0	4	4	5
# 3	2	5	9
# 1	7	9	3

# 'b'와 'c' 열을 기준으로 정렬
frame2.sort_values(by=['b', 'c'])

#	b	a	c
# 3	2	5	9
# 2	3	2	7
# 0	4	4	5
# 1	7	9	3

#######################################
obj = Series([100, 23, 55, 44, 22, 99, 33])  # 시리즈 생성
obj

obj.rank()  # 기본적으로는 오름차순으로 순위 매기기

# 0    100
# 1     23
# 2     55
# 3     44
# 4     22
# 5     99
# 6     33

obj.rank(ascending=False)  # 내림차순으로 순위 매기기

# 0    1.0
# 1    6.0
# 2    3.0
# 3    4.0
# 4    7.0
# 5    2.0
# 6    5.0

obj2 = Series([100, 23, 55, 44, 22, 44, 33])  # 시리즈 생성
obj2.rank()  # 기본적으로는 오름차순으로 순위 매기기

# 0    7.0
# 1    2.0
# 2    6.0
# 3    4.5
# 4    1.0
# 5    4.5
# 6    3.0

obj3 = Series([100, 23, 55, 44, 22, 44, 33, 44])  # 시리즈 생성
obj3.rank()  # 기본적으로는 오름차순으로 순위 매기기

# 0    8.0
# 1    2.0
# 2    7.0
# 3    5.0
# 4    1.0
# 5    5.0
# 6    3.0
# 7    5.0

obj3.rank(method="min")  # 동일한 값들 중 첫 번째 값으로 순위 부여

obj3.rank(method="max")  # 동일한 값들 중 마지막 값으로 순위 부여

obj3.rank(method="first")  # 동일한 값들의 순서대로 순위 부여

frame3 = DataFrame({'b':[4, 4, 4, 2], 'a':[4, 9, 2, 5], 'c': [5, 3, 7, 9]})
frame3

frame3.rank()  # () = (axis=0) 열을 기준으로 순위 매기기

#	b	a	c
# 0	3.0	2.0	2.0
# 1	3.0	4.0	1.0
# 2	3.0	1.0	3.0
# 3	1.0	3.0	4.0

frame3.rank(1)  # (1) = (axis=1) 행을 기준으로 순위 매기기

# 	b	a	c
# 0	1.5	1.5	3.0
# 1	2.0	3.0	1.0
# 2	2.0	1.0	3.0
# 3	1.0	2.0	3.0

frame3.sum()  # () = (axis=0) 합 구하기

frame3.sum(1)  # (1) = (axis=1) 합 구하기

frame3.mean()  # (0) = (axis=0) 평균 구하기

frame3.mean(1)  # (0) = (axis=1) 평균 구하기


frame4 = DataFrame({'b':[4, 4, 4, 2], 'a':[4, 9, 2, 5], 'c': [5, 3, 7, np.nan]})
frame4

frame4.rank()  # 기본적으로는 오름차순으로 순위 매기기

#	b	a	c
# 0	3.0	2.0	2.0
# 1	3.0	4.0	1.0
# 2	3.0	1.0	3.0
# 3	1.0	3.0	NaN

frame4.sum()  # 기본적으로는 열을 기준으로 합 구하기 (axis=0)

# b    14.0
# a    20.0
# c    15.0

frame4.mean()  # 기본적으로는 열을 기준으로 평균 구하기 (axis=0)

# b    3.5
# a    5.0
# c    5.0

frame4.sum(skipna=False)  # NaN이 있을시 NAN

# b    14.0
# a    20.0
# c     NaN

cl = frame4.dropna()  # NaN이 있는 행 제거
cl
 ==
cl = frame4.dropna(axis=0)  # NaN이 있는 행 제거
cl
#	b	a	c
# 0	4	4	5.0
# 1	4	9	3.0
# 2	4	2	7.0

cl = frame4.dropna(axis=1)  # NaN이 있는 열 제거
cl

#	b	a
# 0	4	4
# 1	4	9
# 2	4	2
# 3	2	5

result = frame4.fillna(77)  # NaN을 77로 채우기
result

# b	a	c
# 0	4	4	5.0
# 1	4	9	3.0
# 2	4	2	7.0
# 3	2	5	77.0

frame4.idxmax(1)  # 행을 기준으로 최대값의 위치 찾기
# 0    c
# 1    a
# 2    c
# 3    a

frame4.idxmax(0)  # 열을 기준으로 최대값의 위치 찾기

# b    0
# a    1
# c    2

frame4.idxmin(1)  # 행을 기준으로 최소값의 위치 찾기

# 0    b
# 1    c
# 2    a
# 3    b

frame5 = DataFrame([[NA, 6.5, 3.], [NA, NA, NA], [NA, 6.5, 3.]])
frame5             

cl = frame5.dropna(axis=1)  # NaN이 없는 열만 남기기
cl

# 0
# 1
# 2

cl = frame5.dropna(axis=1, how='all')  # 모든 값이 NaN인 열만 제거
cl

#            1          2
# 0	6.5	3.0
# 1	<NA>	<NA>
# 2	6.5	3.0

cl = frame5.dropna(axis=0, how='all')  # 모든 값이 NaN인 행만 제거
cl

#	0	1	2
# 0	<NA>	6.5	3.0
# 2	<NA>	6.5	3.0

result = frame5.fillna(0)  # NaN을 0으로 채우기
result

# 	0	1	2
# 0	0	6.5	3.0
# 1	0	0.0	0.0
# 2	0	6.5	3.0

# 주어진 데이터를 포함하는 Series 객체 생성
obj3 = pd.Series(['c', 'a', 'd', 'a', 'a', 'c', 'b', 'b', 'c', 'c'])
print(obj3)

# Series 객체를 CSV 파일로 저장하여 './file2.csv'에 저장하며, 헤더를 포함하고 인덱스는 저장하지 않음
obj3.to_csv('./file2.csv', header=True, index=False, encoding='utf-8')

# CSV 파일에서 데이터를 읽어와 DataFrame 객체로 변환
df2 = pd.read_csv('./file2.csv', sep=',')
print(df2)

# 	0
# 0	c
# 1	a
# 2	d
# 3	a
# 4	a
# 5	c
# 6	b
# 7	b
# 8	c
# 9	c

obj3.unique() # 중복 한번만 출력

obj3.value_counts() # 해당 valus : 개수 (정렬 x)
==
obj3.value_counts(sort=False)  # (정렬 x)

# c    4
# a    3
# b    2
# d    1

obj3.isin(['b']) # b랑 같은것이 있을시 ture

# 0    False
# 1    False
# 2    False
# 3    False
# 4    False
# 5    False
# 6     True
# 7     True
# 8    False
# 9    False

obj.isin(['b','c']) # b와 c만 ture

mask = obj3.isin(['b','c']);mask #b와c만 있는값만 배열로 취급
obj3[mask]

# 0    c
# 5    c
# 6    b
# 7    b
# 8    c
# 9    c
