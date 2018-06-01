a=1
b=1.5

f = @(x) exp(-x^2);

ret = (b-a) * f((a+b)/2)