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


