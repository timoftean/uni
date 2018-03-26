x=-1:0.01:3

P=@(x,k) x.^k/factorial(k);
n=10
s=0
for i = 0:n
    r1=P(x,i)
    s=s+r1
end
plot(x,s)