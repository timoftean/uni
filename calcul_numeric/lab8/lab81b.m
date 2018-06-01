%{
a=1
b=1.5

f = @(x) exp(-x.^2);

ret = (b-a) * f((a+b)/2)

n=150;
x = linspace(a, b, n+1);
ret = ((b - a) / n) * sum(f(x(2:n)))


n=500;
x = linspace(a, b, n+1);
ret = ((b - a) / n) *  sum(f(x(2:n))) 
%}

a = 1;
b = 1.5;
 
f = @ (x) exp(-x.^2);
 
res = (b-a) * f((a+b)/2)
 
n = 150;
arr=zeros(1,n);
arr(1) = a + (b-a)/(2*n);
for i=2:n
    arr(i) = arr(1)+(i-1)*(b-a)/n;
end
 
res2 = ((b-a)/n)*sum(f(arr))