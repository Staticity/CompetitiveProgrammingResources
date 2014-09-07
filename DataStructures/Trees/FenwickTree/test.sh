javac *.java && java -ea FenwickTreeTest < test.in > out.txt
diff -u --ignore-all-space out.txt test.out
rm *.class
rm out.txt