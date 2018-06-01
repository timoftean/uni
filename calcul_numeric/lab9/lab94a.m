for n=10:15
  v = [];
  for j = 1:n
    sir = [1:n];
    v(:,j) = (1./sir).^(j-1);
  end
  fprintf('n = %d    %f\n', n, cond(v))
end