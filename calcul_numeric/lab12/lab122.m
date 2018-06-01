f = @(x)x^3-x^2-1;
e = 10^(-4);
n = 100;
step = 1;
 
x0 = 1;
x1 = 2;
 
while(abs(f(x1)) > e && step < n)
    x2 = x1 - f(x1)*((x1-x0)/(f(x1)-f(x0)));
    step = step + 1;
    x0 = x1;
    x1 = x2;
end
 
x2