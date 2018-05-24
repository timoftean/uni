r = 110;
p = 75;
 
f = @ (x) (1-(p/r).^2*sin(x)).^(1/2);
 
a = 0;
b = 2*pi;
 
n=10;
 
h=(b-a)/n;
x=zeros(1,n-1);
 
for i=1:n-1
    x(i)=a+i*h
end
 
trap = (b-a)/(2*n)*(f(a)+f(b) + 2*sum(f(x)));
 
result = (60*r)/(r^2-p^2) * trap