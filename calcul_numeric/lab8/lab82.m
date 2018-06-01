arr = zeros(1,150);
dif = 1;
k = 2;
f = @(x) 2/(1+x^2);
 
a = 0;
b = 1;
h = b-a;
t0 = (h/2)*(f(a)+f(b));
t1 = (h/4)*(f(a)+2*f(a+h/2)+f(b));
 
while(abs(t0-t1) > 0.00001)
   
    arr=zeros(1,2^(k-1));
    for j=1:2^(k-1)
        arr(j) = f(a+(2*j-1)/(2^k)*h);
    end
   
    t2 = (1/2)*t1+(h/(2^k))*sum(arr);
    t0 = t1;
    t1 = t2;
    k = k + 1;
   
    simp = (1/3)*(4*t1-t0);
end
fprintf("First guy %f\n", t1)
fprintf("Second guy %f\n", simp)