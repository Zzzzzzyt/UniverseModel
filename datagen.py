import random

f=open('D:/MyJava/UniverseModel/testdata.txt','w')
f.write('500\n')
for i in range(500):
	s='%d %d %d %d %d %d %d\n'%(random.randint(-1e12,1e12),random.randint(-1e12,1e12),0,random.randint(-10,10),random.randint(-10,10),0,1e16)
	f.write(s)
f.close()
