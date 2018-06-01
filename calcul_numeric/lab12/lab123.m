
f = @(x)(x-2)^2-log(x);

n=100
epsilon=10^-4
a=1
b=2

for i=1:n
    c = (a + b) / 2
    if (f(a) * f(c) <= 0)
        b = c
    else
        a = c

    end
    if(abs(a-b) < epsilon)
        break
        
    end
end
a
b