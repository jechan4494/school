import numpy as np
a = np.array([[0,1,2],[3,4,5]])
print('a\n',a)

b= a.transpose() #전치행렬을 만드는 메서드
print('b\n',b)
c =a.T
print('c\n',c)

a = np.random.rand(4,2)
a
a = np.random.rand(4,2)
a
a = np.random.randint(1,10,(4,2))
a
mask = np.array([0,1,1,0],dtype=bool)
print(mask)

import random as r #랜덤->pattern
print(r.random()) # 0~1사이
print(r.randint(1,10)) # 1~10사이
print(r.randrange(1,10)) # 1~9사이

a =np.random.rand(4,2) #4행2열의 0~1사이 랜덤
a

a = np.random.randn(4,2)
a

mask = np.array([0,1,1,0],dtype=bool)
print(mask)

#a = np.random.ran(4,2)
#print('\ndata 출력\n',a)
#print('\n마스킹된 데이터 출력\n',a[mask])
#print('\n마스킹 역전된 데이터 출력',a[~mask])

a =np.array([3,2,5,1,4])
print('원본\n',a)
print('정렬 후\n',np.sort(a))
print('정렬한 인덱스\n',np.argsort(a))

a.sort() # = np.sort(a)
print('원본\n',a)

np.linspace(1,10,10) #0~10사이의 똑같은 구간을 10개 만들어라

np.linspace(0,1,20) #0에서1사이의 똑같은 구간을 20개만들어라
a=np.random.randint(-10,0,(4,2)) #-10부터 0사이의 구간을 4행2열로 만들어라
a
np.abs(a)
b= np.abs(a)
b
np.sqrt(a) #루트를 씌워라
np.add(a,a) #행끼리 계산
