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

b=pd.Series([1,2,3],index=['a','b','c'])
print(b)

region1 = pd.Series(['서울','부산','대구','대전','광주'])
region1

region2=pd.Series(['서울','부산','대구','대전','광주'],index=['a','b','c','d','500'])
region2

import pandas as pd
dic3 = {'city' : ['서울'], 'year':[2018]}
data3 =pd.Series(dic3); data3

dic4 = {'city' : ['서울'], 'year':[2018]}
data4 =pd.DataFrame(dic4); data4

dic=dic3 = {'city' :['서울','부산','대구','대전','광주'],
            'year' : [2017,2017,2018,2018,2018],
            'temp' :[18,20,19,21,20]}
data6 =pd.Series(dic); data6

import pandas as pd
dic5 = {'city' :['서울','부산','대구','대전','광주'],
            'year' : [2017,2017,2018,2018,2018],
            'temp' :[18,20,19,21,20]}
data6 =pd.Series(dic5); data6

data5 = pd.DataFrame(dic5)

data5['city']

data5.city # 객체지향 city열만 출력

data5.set_index('city')
data5

data5.index=['가','a',5.2,'라','기린'] ; # data5 #index이름을 바꿔라 

data5.columns=['도시','연도','날씨'] ; # data5 # 도시 연도 날씨로 바꿔라

#data['city'] # 바뀌었기때문에 출력 불가

data5['도시'] # 바꾼값 출력 가능

data5[['도시','날씨']]

data5.도시

data5.loc['기린'] #index 이름으로 접근한다 .loc
 
data5.loc[5.2]

data5.set_index('도시') # set_index원본을 바꾸진않고 바꾼 상태를 일시적으로 보여줌(미리보기) 
data5

data8=data5.set_index('도시'); data8 # 도시에 있는 값들을 index로 설정하는것을 data8에 저장
data5.set_index('도시',inplace=True); data5 #inplace=True 원본을 바꿔줌(실제 바꿈)

data5.loc['대전']

data5.iloc[1:4] #1행에서3행까지 출력 

cars =[50,40,20,30,10]
data5['car'] = cars #car데이터로 추가해라
data5

data5['hight1'] = data5.car >=30 # hight1를 추가해되 30보다 클 시 true아니면 false
data5

data5['hight2'] = data5.car>=40; data5 

data5.drop('hight1',axis=1) # 열단위로 삭제해라(실제 삭제안됨) drop, axis=0(에러), axis x (에러)
data5 

a=data5.drop('hight2',axis=1) # 삭제하는 방식을 a에 저장

data5.drop('hight1',axis=1,inplace=True) #실제로 삭제해라 inplace=True
data5

data5.drop('대구',axis=0,inplace=True) # 대구 idex 행이 사라짐
data5

#################################################################

import pandas as pd
import numpy as np

b = pd.Series([1,2,3],index =['a','b','c'])
print(b)

# 행에서 최대값 - 최솟값
def f1(x):
    k=x.max() - x.min() 
    return k
    
def f2(x):
    return x.max() - x.min()

f3 = lambda x: x.max() - x.min() # f3 : 함수 이름 , x : 매개변수 , x.max() - x.min():함수내용
#f1 == f2 == f3

df = pd.DataFrame(np.arange(12).reshape(4,3), # arange() 1차원으로 만들어라 reshape() 2차원 배열의 형태를 바꿔라 4행3열
                  columns=['A열','B열','C열'],
                  index=['a','b','c','d'])
df

df.apply(f2) # 행기준 (기본은 열기준)

df.apply(f3,0) # 행기준 

df.apply(f3,1) # 열기준

frame = DataFrame(np.arange(8).reshape(2,4),
                  index=['three','one'],
                  columns=['d','a','가','c'])
frame

frame.sort_index() # sort_index index를 오름차순으로 정렬
frame2 = frame.sort_index(axis=0) # key값 정렬 행기준 
frame2

frame.sort_index(axis=1) # key값 정렬 열기준

frame.sort_index(axis=1,ascending=False)

frame.sort_index(axis=1,ascending=False,inplace=True)
frame # inplace를 실행시 기본값 변경 데이터 frame 입력

frame.sort_values(by='가',ascending=False)

frame2=DataFrame({'b':[4,7,3,2],'a':[4,9,2,5],'c':[5,3,7,9]})
frame2

frame2.sort_values(by='b')

frame2.sort_values(by='a')

frame2.sort_values(by=['b','c'])

#######################################

obj =Series([100,23,55,44,22,99,33]); obj

obj.rank()

obj.rank(ascending=False)

obj2=Series([100,23,55,44,22,44,33]);obj
obj2.rank()

obj3=Series([100,23,55,44,22,44,33,44]);obj
obj3.rank()

obj3.rank(method="min") # 똑같은 값들중 첫 값으로 등수를 통일

obj3.rank(method="max") # 똑같은 값들중 마지막 등수로 통일

obj3.rank(method="first") # 똑같은값 순차적으로 등수

frame3 = DataFrame({'b':[4,4,4,2], 'a':[4,9,2,5], 'c' : [5,3,7,9]})
frame3

frame3.rank() # () = (axis=0)

frame3.rank(1) # (1) = (axis=1)

frame3.sum() # () = (axis=0) 합

frame3.sum(1) # (1) = (axis=1) 합

frame3.mean() # (0) = (axis=0) 평균

frame3.mean(1) # (0) = (axis=1) 평균

frame4 = DataFrame({'b':[4,4,4,2], 'a':[4,9,2,5], 'c' : [5,3,7,np.nan]})
frame4

frame4.rank()

frame4.sum()

frame4.mean()

frame4.sum(skipna=False)

cl= frame4.dropna();cl #nan이 있는 행이 사라짐

cl=frame4.dropna(axis=0);cl #nan이 있는 행이 사라짐

cl=frame4.dropna(axis=1);cl #nan이 있는 열이 사라짐

result = frame4.fillna(77); result # nan이 있는곳을 77로 채움

frame4.idxmax(1) # 열기준에서 최대값

frame4.idxmax(0) # 행기준에서 최대값

frame4.idxmin(1)  # 열기준에서 최소값

frame5 = DataFrame([[NA,6.5,3.],[NA,NA,NA],[NA,6.5,3]])
frame5             

cl= frame5.dropna(axis=1);cl #

cl= frame5.dropna(axis=1,how='all');cl # 모두 nan인것만 지움 열기준

cl= frame5.dropna(axis=0,how='all');cl

result= frame5.fillna(0); result #nan열을 0으로 채움


