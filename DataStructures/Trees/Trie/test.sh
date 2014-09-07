javac *.java && java -ea TrieTest < test.in > out.txt
diff -u --ignore-all-space out.txt test.out
rm *.class
rm out.txt