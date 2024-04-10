import numpy as np
a = np.array([[0,1,2],[3,4,5]])
print('a\n',a)

b= a.transpose() #전치행렬을 만드는 메서드
print('b\n',b)
c =a.T # 전치행렬을 만드는 메서드
print('c\n',c)

# 0부터 1 사이의 난수로 이루어진 4x2 배열 생성
a = np.random.rand(4, 2)
print("랜덤 배열 'a':")
print(a)

# 1부터 9 사이의 난수로 이루어진 4x2 배열 생성
a = np.random.randint(1, 10, (4, 2))
print("\n1부터 9 사이의 정수 배열 'a':")
print(a)

# 불리언 마스크 배열 생성
mask = np.array([0, 1, 1, 0], dtype=bool) # 1 true 출력 0 flase 출력
print("\n불리언 마스크 배열:")
print(mask)

import random as r #랜덤->pattern
print(r.random()) # 0~1사이
print(r.randint(1,10)) # 1~10사이
print(r.randrange(1,10)) # 1~9사이

a = np.random.rand(4,2)
print('\ndata 출력\n',a)
print('\n마스킹된 데이터 출력\n',a[mask])
print('\n마스킹 역전된 데이터 출력',a[~mask])

a =np.array([3,2,5,1,4])
print('원본\n',a)
print('정렬 후\n',np.sort(a))
print('정렬한 인덱스\n',np.argsort(a))

# 결과
# 원본
# [3 2 5 1 4]
# 정렬 후
# [1 2 3 4 5]
# 정렬한 인덱스
# [3 1 0 4 2]

a.sort() # = np.sort(a)
print('원본\n',a)

np.linspace(1,10,10) #0~10사이의 똑같은 구간을 10개 만들어라

# 결과 : array([ 1.,  2.,  3.,  4.,  5.,  6.,  7.,  8.,  9., 10.])

np.linspace(0,1,20) #0에서1사이의 똑같은 구간을 20개만들어라

#array([0.        , 0.05263158, 0.10526316, 0.15789474, 0.21052632,
#      0.26315789, 0.31578947, 0.36842105, 0.42105263, 0.47368421,
#       0.52631579, 0.57894737, 0.63157895, 0.68421053, 0.73684211,
#       0.78947368, 0.84210526, 0.89473684, 0.94736842, 1.        ])

a=np.random.randint(-10,0,(4,2)) #-10부터 0사이의 구간을 4행2열로 만들어라
a

#array([[-2, -4],
#       [-6, -6],
#       [-9, -3],
#       [-2, -8]])

np.abs(a)  # 배열 a의 각 요소에 대한 절댓값 계산
b = np.abs(a)  # 배열 a의 각 요소에 대한 절댓값을 새로운 배열 b에 저장
print(b)

np.sqrt(a)  # 배열 a의 각 요소에 대한 제곱근 계산
np.add(a, a)  # 배열 a와 배열 a의 각 요소를 더한 결과를 반환 (행렬의 행끼리 계산)
