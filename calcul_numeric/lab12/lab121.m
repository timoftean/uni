f = @(x)x-cos(x);

derf = @(x)1+sin(x);

e = 10^(-4);
n = 100;
step = 1;
x0 = pi/4;
 
current_e = e;
diff = x0;
 
while(diff > e && step < n)
    x1 = x0 - f(x0)/derf(x0);
    step = step + 1;
    diff = x1-x0;
    x0 = x1;
end
step
x1