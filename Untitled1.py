#pandas -> 표 <- dictionary
# 1. 시리즈 : 1차원 
# 2. 데이터프레임 : 2차원 
# Dic key 이름이 -> ㅇ

import numpy as np
import pandas as pd
from numpy import *
from pandas import *

a =pd.series([1,2,3,4])
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
data6 =pd.Series(dic6); data6

import pandas as pd
dic5 = {'city' :['서울','부산','대구','대전','광주'],
            'year' : [2017,2017,2018,2018,2018],
            'temp' :[18,20,19,21,20]}
data6 =pd.Series(dic5); data6

data5 = pd.DataFrame(dic5) ; data5

data5['도시']

data[['도시','날씨']]

data5.도시

dat5.loc['기린']

data5.loc[5.2]

data.set_index('도시')
data5

a =data5['cirty']; a

data5.index=['가','a',5.2,'라','기린'] ; data5 #index이름을 바꿔라

data5.columns=['도시','연도','날씨'] ; data5 #도시 연도 날씨로 바꿔라

#data['city']

data5['도시']

data5[['도시','날씨']]

data5.도시

data5.loc['기린'] #index 이름으로 접근한다 .loc
 
data5.loc[5.2]

data5.set_index('도시') #set_index원본을 바꾸진않고 바꾼 상태를 일시적으로 보여줌(미리보기) 
data5

data=data5.set_index('도시'), data8
data5.set_index('도시',inplace=True); data5 #inplace=True 원본을 바꿔줌(실제 바꿈)

data5.loc['대전']
data

data5.iloc[1:4] #1행에서3행까지 출력 

cars =[50,40,20,30,10]
data5['car'] = cars
data5

data5['hight1'] = data5.car >=30
data5

data5['hight2'] = data5.car>=40; data5

data5.drop(hight1,axis=1)
data5

a=data5.drop('hight2',axis=1)
data5.drop('hight1',axis=1,inplace=True)
