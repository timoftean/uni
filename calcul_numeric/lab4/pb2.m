x = linspace(0,6,13);
f = exp(sin(x))
Y=f';
 
a=0:0.01:6;
y=zeros(1,length(a));
 for i=1:length(a)
   y(i) =  newton(a(i),x,Y);
 end
 hold on
 plot(x,Y,'x')
 plot(a,y)