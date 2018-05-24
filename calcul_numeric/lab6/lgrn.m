function z=lgrn(xnod,ynod,x)
  s1=0;
  s2=0;


m = length(xnod);
s1=0;
s2=0;
X = x;

for i = 1:m
  s1 = s1 + (A(i,xnod)*ynod(i))/(X-xnod(i));
  s2 = s2 + (A(i,xnod))/(X-xnod(i));
end

z=s1/s2;

end