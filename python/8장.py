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
