for n=10:15
  h = [];
  for i = 1:n
    for j=1:n
      h(i,j) = 1/(i+j-1);
    end
  end
  fprintf('n = %d    %f\n', n, cond(h))
end