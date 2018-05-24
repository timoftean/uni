x =[0,10,20,30,40,60,80,100];
y =[0.0061, 0.0123, 0.0234, 0.0424, 0.0738, 0.1992, 0.4736, 1.0133];
x1 = 0:0.1:100;

p1 = polyfit(x,y,2)
p2 = polyfit(x,y,4)

hold on
plot(x,y,'*')
plot(x1,polyval(p1,x1))
plot(x1,polyval(p2,x1))

 y1=zeros(1,length(x1));
 for i=1:length(x1)
   y1(i) =  lgrn(x,y,x1(i));
 end
 plot(x1,y1);
legend ('int point','2nd grade pol', '4th grade pol','polinom lagrange')