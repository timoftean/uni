for n=10:15
  v = [];
  for j = 1:n
    sir = [1:n];
    sir = -1+(2/n).*sir;
    v(:,j) = sir.^(j-1);
  end
  fprintf('n = %d    %f\n', n, cond(v))
end