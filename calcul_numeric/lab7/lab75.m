f = @ (x) 1./(4+sin(20*x));

a = 0;
b = pi;

n = 30;

h=(b-a)/n;
x=zeros(1,n+1);
for i=0:n
    x(i+1)=a+i*h;
end

sim = ((b-a)/(6*n))*(f(a)+f(b)+4*sum(f(x(1:n)/2+x(2:n+1)/2))+2*sum(f(x(2:n))))