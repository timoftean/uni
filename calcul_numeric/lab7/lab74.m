res = 0.6362943688583;
r = 0;
n = 4;
 
f = @ (x) (x.*log(x));
 
a = 1;
b = 2;
 
while abs(res - r) > 0.001
    h=(b-a)/n;
    x=zeros(1,n-1);
    for i=1:n-1
        x(i)=a+i*h
    end
    r = (b-a)/(2*n)*(f(a)+f(b) + 2*sum(f(x)));
    n = n + 1;
end

n