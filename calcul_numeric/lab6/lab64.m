axis([0 3, 0 5])
[x,y] = ginput(10)

hold on
xx=0:0.1:3
p1 = polyfit(x,y,2)
plot(x,y,'o')
plot(xx,polyval(p1,xx))