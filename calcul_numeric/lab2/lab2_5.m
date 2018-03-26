
i=0:4;
x = (2 + 2*i)';
f = [4 8 14 16];

n= length(f);
T = zeros(n);
T(:,1) = f;

for i=2:4
    T(1:n-i+1, i) = diff(T(1:n-i+2,i-1))./(x(i:n) - x(1:n-i+1));
end

T