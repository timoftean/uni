
x = [1,2,3,4,5];
f = [22; 23; 25; 30; 28];
%a
disp(newton(2.5,x,f))

%b
a=1:0.01:5;
y=zeros(1,length(a));
 for i=1:length(a)
   y(i) =  newton(a(i),x,f);
 end
 hold on
 plot(x,f,'x')
 plot(a,y)