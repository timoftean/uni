function r = repsimp(x,a,b,n,f)
  x = linspace(a, b, n + 1);
  r = (b - a) / (6 * n) * (f(a) + f(b) + 2 * sum(f(x(2:n))) + 4 * sum(f((x(1:n) + x(2:n+1))/2)));
end