데이터전처리 - 쓸모없는 값을 실행전 수정하는 것(NaN)

df = pd.DataFrame({'A' : [1,2,np.nan,4,5],
                   'B' : [6,7,8,np.nan,10],
                   'C' : [11,12,13,np.nan,np.nan]})
df

pd.isna(df)

pd.isna(df).sum() # nan의 합을 출력

df_drop_nan = df.dropna()
df_drop_nan

df_drop_B_C = df.dropna(subset=['B','C'])
df_drop_B_C

pd.isna(df)

df_0 = df['C'].fillna(0)
print(df_0)

df_missing = df['A'].fillna('missing')
df_missing

#df.fillna(df.mean(), inplace = True)
df_mean = df.fillna(df.mean())
print(df,'\n')
print(df_mean)

print(df,'\n')
df_fill = df.fillna(method = 'ffill') # NAN값을 전 값으로 채움
print(df_fill,'\n')

df_bfill = df.fillna(method = 'bfill') # NAN값을 다음값으로 채움
print(df_b)

fill_dict = {'A':df['A'].mean(),'B':'12/25','C':'missing'} # NAN값을 A의 평균,12/25,MISSING으로 채움 
df_filled= df.fillna(val)

이상치를 제거하는것 박스플롯,IQR(분위수Q1 - 25, Q2 - 50, Q1- 1.5 * IQR = 최솟값, Q3 +1.5 * IQR = 최댓값

import os
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import warnings
from datetime import *
warnings.filterwarnings('ignore')

path = './염쨩/' # 
file_list = os.listdir(path) # 탐색기 
file_list

file_list_py = [file for file in file_list if file.endswith('.xlsx')] # 엑셀 파일만 확인
file_list_py

file_list_py = [file for file in file_list if file.endswith('.xlsx')]
file_list_py

df = pd.DataFrame() # 공백 프레임 만듬
for i in file_list_py: # 
    data = pd.read_excel(path + i) # 한개씩 데이터를 읽음
    df = pd.concat([df,data]) # 빈공백에 한개씩 읽은 데이터 concat함 (이어붙힘)
df

df.head(3) # 데이터 앞에 3개만 보여줌

df.tail() # 데이터 뒤에 5개만 보여줌

df = df.reset_index(drop = True) ;  df # 기존 인덱스를 열로 바꿔라 

df.info() # 어떤 값들로 이루어져있는지에 대한 정보

df['Data'] = pd.to_datetime(df['날짜'],format = '%Y%m%d') +\ # 데이터 열을 만듬 판다스의 데이터 타입을 읽어들여서 Y M D 값과 
             pd. to_timedelta(df['시간'].astype(int),unit='h')
df.set_index(df['Data'],inplace = True)
df.head(3) 
