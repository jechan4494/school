Data = [5,2,7,3]
A=0
for i in range(0,3):
    A +=Data[i]
    print('A=',A)
# A= 5
# A= 7
# A= 14

x,y =5,3
def func(x,y):
    t=x;x=y;y=t
    print ('x=',x,'y=',y)
print("x=",x,"y=",y)
func(x,y)
print(x,y)

def div(a,b=3):
    return a/b
print('div(4) =',div(4))
print('div(9,2)',div(6,4))

x = 100; y=200
x,y =y,x
print("x=",x,"y=",y)

z = (1,2,3)
x = z+z
print(x)
z = z+3
print(z)

import numpy as np
a = [1,2,3]; b = [4,5,6]
a2= np.array(a)
b2 = np.array(b)
print("a+b",a+b)
print("a2+b2",a2+b2)

import numpy
data =numpy.array([[1,2,3],[4,5,6],[7,8,9],[10,11,12]])
x,y =data[:,:-1],data[1:,:]
print("x",x)
print("y",y)
print("shape",data.shape)
print("ndim",data.ndim)
print("[2,-1]",data[2,-1])

for i in range(1,30,2):
    for k in range(2,8,3):
        print(i,"\t",k,"\t")
        if i==3:
            break;
print("끝")

b= ["지","경","찬","성","Ja"]
b.sort()
print(b)
b.reverse()
print(b)

import pandas
di={"학번":[123,4],"이름":['홍길동','장동건']}
da =pandas.Series(di);print(da)
da2=pandas.DataFrame(di); print(da2)

b = pandas.Series(['c','a','d','a','c','b','b','c','c'])
b.value_counts()

a = 123
if a>100:
    if a>200:
        print("큰")
    else:
        print("적당")
else:
    print("작음")
print("끝")

dong=[1,2,3,4]
dong[-2]       
dong[-1:-3]
dong[:3]
dong[2:]
sum(dong)
dong.remove(3)

dong=[1,2,3,4]
dong.pop()
dong
del dong[3]

import random 
dong.append(random.randint(1,10))
dong

dong.insert(3,7)
dong
