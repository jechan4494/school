import platform

from matplotlib import font_manager, rc 

import matplotlib

# '-' 부호가 제대로 표시되게 하는 설정 

matplotlib.rcParams['axes.unicode_minus'] = False

# 운영 체제마다 한글이 보이게 하는 설정 # 윈도우

if platform.system() == 'Windows':
    print("ㅇㅇ")

    path = "c:\Windows\Fonts\malgun.ttf"

    font_name = font_manager.FontProperties(fname=path).get_name() 

    rc('font', family=font_name) 

elif platform.system() == 'Darwin':  #맥

    rc('font', family='AppleGothic')

elif platform.system() == 'Linux': # 리눅스

    rc('font', family='NanumBarunGothic')

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

plt.figure(figsize=(3,2))  # 그림의 크기를 가로 3, 세로 2로 설정합니다.
plt.title("Plot")  # 그래프의 제목을 "Plot"으로 설정합니다.
plt.plot([1,4,9,16])  # 선 그래프를 그립니다.
plt.show()  # 그래프를 화면에 출력합니다.

plt.figure(figsize=(2,1))
plt.title("plot")
plt.plot([25,19,9,16])  # x값이 주어지지 않으면 자동으로 0, 1, 2, 3이 사용됩니다.
plt.show()

plt.rcParams['axes.unicode_minus'] = False  # 축의 음수 기호 표시를 설정합니다.

plt.plot([-1,2,3,4])  # 선 그래프를 그립니다.
plt.title('대한민국')  # 그래프의 제목을 "대한민국"으로 설정합니다.
plt.xlim([-5,5])  # x 축의 범위를 -5부터 5까지로 설정합니다.
plt.show()  # 그래프를 화면에 출력합니다.

plt.figure(figsize=(2,2))  # 그림의 크기를 가로 2, 세로 2로 설정합니다.
plt.title("한글 제목")  # 그래프의 제목을 "한글 제목"으로 설정합니다.
plt.plot([10,20,30,40],[100,4,9,16])  # x 값은 [10,20,30,40], y 값은 [100,4,9,16]으로 지정하여 선 그래프를 그립니다.
plt.show()  # 그래프를 화면에 출력합니다.

#색이름, 약자, #RGB코드 사용한다
#blue [b] green [g] red [r] cyan [c] magenta [m] yellow [y] black [k] whithe [w]

plt.plot([10,20,30,40],[1,4,9,16],"bo-.")  # x 값은 [10,20,30,40], y 값은 [1,4,9,16]으로 지정하여 파란색 원형 마커를 사용한 점선 그래프를 그립니다.
plt.show()  # 그래프를 화면에 출력합니다.

plt.plot([10,20,30,40],[1,4,9,16],c="b",lw=5,ls="-.",  # x 값은 [10,20,30,40], y 값은 [1,4,9,16]으로 지정하여 파란색, 두께 5, 점선으로 그래프를 그립니다.
         marker="o",ms=10,mec="g",mew=5,mfc="#ff00ff")  # 원형 마커를 사용하며, 크기는 10, 마커의 선 색깔은 초록색, 선의 굵기는 5, 마커의 내부 색깔은 자홍색(#ff00ff)으로 설정합니다.
plt.show()  # 그래프를 화면에 출력합니다.

X = np.linspace(-np.pi, np.pi, 10)  # -π부터 π까지를 10개의 구간으로 나눈 배열을 생성합니다.
print(X)  # 생성된 배열을 출력합니다.

C = np.cos(X)  # X 배열에 대한 코사인 값을 계산하여 배열 C를 생성합니다.
print(C)  # 생성된 배열 C를 출력합니다.

mport numpy as np  # numpy 모듈을 가져옵니다.
import matplotlib.pyplot as plt  # matplotlib의 pyplot 모듈을 가져옵니다.

X = np.linspace(-np.pi, np.pi, 256)  # -π부터 π까지를 256개의 구간으로 나눈 배열을 생성합니다.
C = np.cos(X)  # X 배열에 대한 코사인 값을 계산하여 배열을 생성합니다.

plt.title("x축과 y축의 tick label 설정")  # 그래프의 제목을 설정합니다.
plt.plot(X, C)  # X와 C를 이용하여 선 그래프를 그립니다.
plt.xlabel("x축 이름")  # x축의 라벨을 설정합니다.
plt.ylabel("y축 이름")  # y축의 라벨을 설정합니다.
plt.xticks([-np.pi, -np.pi/2, 0, np.pi/2, np.pi])  # x축의 눈금을 설정합니다.
plt.yticks([-1, 0, +1])  # y축의 눈금을 설정합니다.
plt.show()  # 그래프를 화면에 출력합니다.

t = np.arange(0., 5., 0.2)  # 0부터 5까지 0.2 간격으로 배열을 생성합니다.
print(t)  # t 배열을 출력합니다.

# 세 개의 데이터셋을 한 번에 그래프로 그립니다.
plt.plot(t, t, 'r--',    # 빨간색 점선 그래프를 그립니다. (x=t, y=t)
         t, 0.5*t**2, 'bs:',    # 파란색 사각형 마커를 사용한 점선 그래프를 그립니다. (x=t, y=0.5*t**2)
         t, 0.2*t**3, 'g^-')    # 초록색 삼각형 마커를 사용한 점선 그래프를 그립니다. (x=t, y=0.2*t**3)

plt.show()  # 그래프를 화면에 출력합니다.

t =np.arange(0.,   5.,0.2) # 0에서 5.0사이에 0.2간격으로
print(t)
plt.plot(t,t,'r--', label="a")    
plt.plot(t,0.5*t**2,'bs:', label="b") # 2차 함수  
plt.plot(t,0.2*t**3,'g^-', label="c") # 3차 함수
plt.legend(loc=0) # 범례 위치를 나타냄

np.random.seed(1)  # 랜덤 시드를 1로 설정합니다.

# 2x2 행렬 모양의 서브플롯을 생성합니다.
plt.subplot(221)  # 첫 번째 서브플롯을 설정합니다.
plt.plot(np.random.rand(5))  # 랜덤한 값으로 선 그래프를 그립니다.
plt.title("axes 1")  # 첫 번째 서브플롯의 제목을 설정합니다.

plt.subplot(222)  # 두 번째 서브플롯을 설정합니다.
plt.plot(np.random.rand(5))  # 랜덤한 값으로 선 그래프를 그립니다.
plt.title("axes 2")  # 두 번째 서브플롯의 제목을 설정합니다.

plt.subplot(223)  # 세 번째 서브플롯을 설정합니다.
plt.plot(np.random.rand(5))  # 랜덤한 값으로 선 그래프를 그립니다.
plt.title("axes 3")  # 세 번째 서브플롯의 제목을 설정합니다.

plt.subplot(224)  # 네 번째 서브플롯을 설정합니다.
plt.plot(np.random.rand(5))  # 랜덤한 값으로 선 그래프를 그립니다.
plt.title("axes 4")  # 네 번째 서브플롯의 제목을 설정합니다.

plt.tight_layout()  # 서브플롯 간의 간격을 조정하여 레이아웃을 더 조밀하게 만듭니다.
plt.show()  # 그래프를 화면에 출력합니다.

fig2 = plt.figure()  # 새로운 figure 객체를 생성합니다.

# 2x2 행렬 모양의 서브플롯을 생성하고 각각의 서브플롯을 변수에 할당합니다.
ax1 = plt.subplot(221)  # 첫 번째 서브플롯을 설정합니다.
ax2 = plt.subplot(222)  # 두 번째 서브플롯을 설정합니다.
ax3 = plt.subplot(223)  # 세 번째 서브플롯을 설정합니다.
ax4 = plt.subplot(224)  # 네 번째 서브플롯을 설정합니다.

# 각 서브플롯에 데이터를 시각화합니다.
ax1.hist(np.random.randn(200), bins=5, color='k', rwidth=0.5)  # 첫 번째 서브플롯에 히스토그램을 그립니다.
ax2.scatter(np.arange(30), np.arange(30) + 3*np.random.randn(30))  # 두 번째 서브플롯에 산포도를 그립니다.
ax3.plot(np.arange(10), np.random.randn(10))  # 세 번째 서브플롯에 선 그래프를 그립니다.
ax4.hist(np.random.randint(1, 7, 1000), bins=6, width=1)  # 네 번째 서브플롯에 히스토그램을 그립니다.

fig2  # 생성한 figure 객체를 출력합니다.

# 6x4 크기의 데이터프레임을 생성합니다. 데이터는 0에서 1 사이의 난수로 채워집니다.
# index로 'one', 'two', 'three', 'four', 'five', 'six'를, columns로 'A', 'B', 'C', 'D'를 가지며, 'Genus'라는 이름을 가진 열 인덱스를 가집니다.
df = pd.DataFrame(np.random.rand(6, 4),
                  index=['one', 'two', 'three', 'four', 'five', 'six'],
                  columns=pd.Index(['A', 'B', 'C', 'D'], name='Genus'))

df  # 데이터프레임을 출력합니다.

df.plot(kind='bar')  # kind='barh'는 수 막대 그래프를 생성합니다.
plt.show()

df.plot(kind='barh')  # kind='barh'는 수평 막대 그래프를 생성합니다.
plt.show()  # 그래프를 화면에 출력합니다.

df.plot(kind='bar', stacked=True)  # kind='bar'는 막대 그래프를 생성하며, stacked=True로 설정하여 값을 쌓습니다.
plt.show()  # 그래프를 화면에 출력합니다.

np.random.seed(10)
data =np.random.randn(2,100)

fig, axs = plt.subplots(2,2,figsize=(5,5))

axs[0,0].hist(data[0])
axs[1,0].scatter(data[0],data[1])
axs[0,0].plot(data[0],data[1])
axs[0,0].hist2d(data[0],data[1])

plt.show()

x=np.random.randint(1,7,1000)
plt.hist(x,bins = 6,width=0.5) 

times =[8,14,2]
timelabels =["Sleep","Study","Play"]
plt.pie(times,labels= timelabels,autopct="%.2f") # pie 원차트
plt.savefig("a.png",dpi=600) # 600해상도로 저장해라
plt.show()

random_data = np.random.randn(100)
plt.boxplot(random_data) 
plt.show()
