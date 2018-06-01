
f = @(x)x-cos(x);

n=100;
epsilon=10^-4;
a=0.5;
b=pi/4;

for i=1:n
    c = b - (b-a)/(f(b)-f(a)) * f(b);
    if (f(a) * f(c) <= 0)
        b = c;
    else
        a = c;

    end
    if(abs(f(c)) < epsilon)
        break
    end
    i
end
i
c