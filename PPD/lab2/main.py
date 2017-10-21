import numpy as np
import threading


n = int(input("n= "))
k = int(input("k= "))
m = int(input("m= "))

print(n , ' works, ',k ,' workers')

times = int( n / k )
rest = int( n % k )
print(times,rest)

#arr - how many tasks performs each thread
arr = []
for i in range(0,k):
    if rest :
        arr.insert(i, times + 1)
        rest -= 1
    else:
        arr.insert(i, times)
print("ARR:", arr)

#interval - what intervals from matrix row will perform each thread
interval = []
contor=0
for i in range(0,k):
    interval.append([contor+1, contor + arr[i]])
    contor += arr[i]
print("interval:", interval)

#generate 2 random matrix
matrix1 = np.random.randint(5, size=(n, k))
matrix2 = np.random.randint(5, size=(k, m))

print('m1:',matrix1)
print('m2:',matrix2)

matrix3 = []

def worker(a,b):
    print "worker...."
    for i in range(a,b-1):
        for j in range(0,n-1):
            for l in range(0, k-1):
                print(matrix1[i][l] * matrix2[l][j])


threads = []
for i in range(0,k-1):
    a,b = interval[i]
    print("i",i,a,b)
    thread = threading.Thread(target=worker(a,b))
    threads.append(thread)
    thread.start()
