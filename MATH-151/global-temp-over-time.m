clear all
A = importdata('data.dat');

t = A(:,1);
y = A(:,2);
plot(t, y, 'o');
title('Average Global Temperature over Time');
xlabel('Years Since 1950');
ylabel('Average Global Temperature (Degrees C)');

p = polyfit(t, y, 2);

hold on

yt = polyval(p, t);
plot(t, yt, '+')
legend('Real Temperature by Year', 'Model Temperature Estimate')

hold off

t2 = 0:0.1;
y2 = polyval(p, t2);
% figure
% plot(t, y, 'o', t2, y2);
% title('title 2');

y3 = polyval(p, t);
res = y - y3;
figure
plot(t, res, '+');
title({'Deviation of Model Predictions from Data', 'Model: f(x)=0.0002x^2-0.0048x+13.8763'});
xlabel('Years Since 1950')
ylabel('Amount of Deviation (Degrees C)')
