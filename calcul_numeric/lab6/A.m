function z = A(i,xnod)
 
p=1;
m = length(xnod);

for j=1:m
  if(j~=i)
    p=p*(xnod(i)-xnod(j));
  end
end
z = 1/p;
end
