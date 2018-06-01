A = [1 1 1 1; 2 3 1 5; -1 1 -5 3 ;3 1  7 -2];
b = [10;  31; -2; 18];

n = length(b)
for i = 1:(n-1)
  [p,q]= max(abs(A(i:4,i))); 
  A([i i+q-1],:) = A([i+q-1 i],:);
  b([i i+q-1],:) = b([i+q-1 i],:);
  for k=i+1:n
    m = A(k,i)/A(i,i);
    A(k,:)= A(k,:)-A(i,:)*m;
    A(k,1)=0;
    b(k) = b(k)-b(i)*m;
  end
end
X = zeros(1,n);
X(n) = b(n)/A(n,n);

for i=(n-1):-1:1
  for j = n:-1:(i+1)
    X(i) = X(i) + A(i,j)*X(j);1
  end
  X(i) = (b(i) - X(i)) / A(i,i);
end

A
X
b