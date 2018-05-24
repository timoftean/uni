x = -3:0.4:3;
y=sin(x);

p1 = polyfit(x,y,4)
p1
xx= -3:0.05:3
hold on
plot(x,y,'*')
plot(xx,polyval(p1,xx))
