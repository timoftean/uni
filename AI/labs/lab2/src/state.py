class State:
    pyramid_size = -1
    pyramid_values = [[[]]]
    pyramid_auxiliar_values = [[[]]]

    def __init__(self):
        fo = open("pyramid.txt","r")
        self.pyramid_size = int(fo.read(2))
        self.pyramid_values = [[[0 for x in range(self.pyramid_size)] for y in range(self.pyramid_size)]
                               for z in range(self.pyramid_size)]
        self.pyramid_auxiliar_values = [[[0 for x in range(self.pyramid_size)] for y in range(self.pyramid_size)]
                                        for z in range(self.pyramid_size)]

        for i in range(0,self.pyramid_size):
            for j in range(0,i+1):
                for k in range(0,i+1):
                    self.pyramid_values[i][j][k] = int(fo.read(2))
                    #print self.pyramid_values[i][j][k]
            fo.read(1)


