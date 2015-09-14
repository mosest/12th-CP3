import Image


class terrain():
    def __init__(self,filename):
        if(filename.find("txt")>0):
            f=file(filename)
            data=[[int(x) for x in y.split(",")] for y in f]
        else:
            img=Image.open(filename).convert("RGB")
            pix=img.load()
            w,h=img.size
            data=[[pix[x,y][0] for x in range(w)] for y in range(h)]
        self.data=data
        self.size=(len(data),len(data[0]))

    def __getitem__(self,(x,y)):
        return self.data[x%len(self.data)][y%len(self.data[0])]

    def __setitem__(self,(x,y),v):
        self.data[x%len(self.data)][y%len(self.data[0])]=v

    def isSlopeOkay(self,(x,y)):
        if abs(self[x,y]-self[x,y-1])>2:
            return False
        if abs(self[x,y]-self[x,y+1])>2:
            return False
        if abs(self[x,y]-self[x-1,y])>2:
            return False
        if abs(self[x,y]-self[x+1,y])>2:
            return False
        return True

    def sumAll(self):
        total=0
        w,h=self.size
        for y in range(h):
            for x in range(w):
                total+=self[x,y]
        return total

    def verifySlope(self):
        w,h=self.size
        for y in range(h):
            for x in range(w):
                if not self.isSlopeOkay((x,y)):
                    return False
        return True
                

    def cost(self,t2):
        diff=0
        w,h=self.size
        for x in range(w):
            for y in range(h):
                diff+=abs(self[x,y]-t2[x,y])
        return diff

t=terrain(raw_input("input file >>"))
t2=terrain(raw_input("output file >>"))
print "INPUT"
ts=t.sumAll()
print "Total dirt: ",ts
print "Slope check: ",t.verifySlope()
print "OUTPUT"
ts2=t2.sumAll()
print "Total dirt: ",ts2
print "Slope check: ",t2.verifySlope()
print
print "Conservation of dirt: ",t.sumAll()==t2.sumAll()
print "Cost: ",t2.cost(t)
