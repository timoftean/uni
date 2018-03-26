function [x,y]=lab3_3()
x=0:0.1:10;
f=@(x)(1+cos(x))./(1+x);
plot(x,f(x));
xx=linspace(0,10,21);
n=length(xx);
ff=zeros(size(xx));
for i=1:n
ff(i)=f(xx(i));
end
y=zeros(size(x));
for i=1:length(x)
y(i)=lab3_1(xx,ff,x(i));
end
hold on;
plot(x,y);
end