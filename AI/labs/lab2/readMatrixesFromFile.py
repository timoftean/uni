#read a list of matrix
# 5
# 6, 7
# 7, 8
# 1, 2, 3
# 4, 5, 6
# 7, 8, 9
class Problem(object):

    def __init__(self):
        self.l=[]

    def readFromFile(self,n):

        with open("file.txt") as fp:
            lines = fp.readlines()

            for m in range(1, n+1):
                matrix = []
                for x in range(0, m):
                    matrix.append(lines.pop(0).rstrip("\n").split(", "))

                self.l.append(matrix)

        print(self.l)
        print(self.l[0][0][0])

p = Problem()
p.readFromFile(3)