import Queue

from state import State

class Problem:
    initial_state = State()
    end_state = State()
    auxiliar = [[[]]]
    values = [[[]]]
    size = -1
    def __init__(self):
        self.auxiliar = self.end_state.pyramid_auxiliar_values
        self.values = self.end_state.pyramid_values
        self.size = self.end_state.pyramid_size
        self.auxiliar[0][0][0] = 1

    def _auxiliar(self,x,y,z):
        return self.auxiliar[x][y][z]

    def _setAuxiliar(self,x,y,z,value):
        self.auxiliar[x][y][z] = value

    def _size(self):
        return self.size

    def _values(self,x,y,z):
        return self.values[x][y][z]

    def _initState(self):
        return self.initial_state

    def _endState(self):
        return self.end_state


class Controller:

    def __init__(self, problem):
        self._problem = problem
        self.dx = [0, 1, 0, 1]
        self.dy = [0, 0, 1, 1]

    def getProblem(self):
        return self._problem

    def orderStates(self, states):
        sortSt = sorted(states)
        return sortSt

    def findSol(self, now, dad):
        ans = [now]
        while now != self._problem._initState:
            now = dad[now]
            ans.insert(0, now)
        return ans

    def gbfs(self):
        path = []
        priorityQueue = Queue.PriorityQueue()
        priorityQueue.put((-self._problem._auxiliar(0,0,0), 0, 0, 0))
        while priorityQueue.not_empty:
            p,x,y,z = priorityQueue.get()
            path.append((x,y,z))
            if z == self._problem._size() - 1:
                return (path, self._problem._auxiliar(x,y,z))
            for k in range(0, 4):
                nz = z + 1
                nx = x + self.dx[k]
                ny = y + self.dy[k]
                maxim = self._problem._auxiliar(x,y,z) + self._problem._values(nx,ny,nz)
                self._problem._setAuxiliar(nx,ny,nz,maxim)
                priorityQueue.put((-maxim, nx, ny, nz))

    def dfs(self):
        stack = []
        path= []
        stack.append((0,0,0))
        while len(stack)>0:
            x,y,z = stack.pop()
            path.append((x,y,z))
            if z == self._problem._size() - 1:
                return (path,self._problem._auxiliar(x,y,z))
            maxim = -1
            for k in range(0,4):
                if maxim < self._problem._auxiliar(x,y,z)+self._problem._values(x+self.dx[k],y+self.dy[k],z+1):
                    nz = z+1
                    nx = x + self.dx[k]
                    ny = y + self.dy[k]
                    maxim = self._problem._auxiliar(x,y,z)+self._problem._values(x+self.dx[k],y+self.dy[k],z+1)

            self._problem._setAuxiliar(nx,ny,nz, self._problem._auxiliar(x,y,z) + self._problem._values(nx,ny,nz))
            stack.append((nx,ny,nz))
        return (path,-1)



class UI:
    def __init__(self, ctrl):
        self._ctrl = ctrl

    def printMainMenu(self):
        print("1. DFS")
        print("2. GBFS")
        print("0. Exit")

    def run(self):
        while True:
            self.printMainMenu()
            op = raw_input("Input command: ")
            if op == "0":
                return
            elif op == "1":
                print("DFS: The maximum path is: \n")
                path,maxim = self._ctrl.dfs()
                print "PATH: ", path
                print "MAXIM: ", maxim
                print "\n"

            elif op == "2":
                print("GBFS: The maximum path is: \n")
                path, maxim = self._ctrl.gbfs()
                print "PATH: ", path
                print "MAXIM: ", maxim
                print "\n"
            else:
                print("Wrong command")


p = Problem()
ctrl = Controller(p)
ui = UI(ctrl)
ui.run()

