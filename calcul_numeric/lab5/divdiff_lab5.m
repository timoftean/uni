function A = divdiff_lab5( x,f,df )
   z = repelem(x,2);
   n = length(z);
   A = zeros(n);
   A(:,1) = repelem(f,2);
   A(1:2:end,2) = df;
   A(2:2:end-2,2) = diff(f) ./ diff(x);
   
   for i=3:n
      A(1:n-i+1,i) = diff(A(1:n-i+2,i-1)) ./ (z(i:n) - z(1:n-i+1))'; 
   end
   
end