import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

public class StackMachine 
{
    public static void main(String[] args)
    {
        try {
            String[] instructions = preprocessCode(args[0]);
            executeCode(instructions);
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    private static String[] preprocessCode(String filePath)
    {
        List<String> lines = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                lines.add(line); // Adiciona cada linha ao ArrayList
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines.toArray(new String[0]);
    }

    private static void executeCode(String[] instructions) throws Exception
    {
        Stack<Float> stack = new Stack<>();
        
        for (String instruction : instructions) {
            String instructionOp = instruction.split(" ")[0];
            if (instructionOp.equals("PUSH")) {
                Float instructionArg = Float.parseFloat(instruction.split(" ")[1]);
                stack.push(instructionArg);
            } else if (instructionOp.equals("MULT")) {
                Float op1 = stack.pop();
                Float op2 = stack.pop();

                stack.push(op1 * op2);
            } else if (instructionOp.equals("DIV")) {
                Float op1 = stack.pop();
                Float op2 = stack.pop();

                stack.push(op2 / op1);
            } else if (instructionOp.equals("SUM")) {
                Float op1 = stack.pop();
                Float op2 = stack.pop();

                stack.push(op1 + op2);
            } else if (instructionOp.equals("SUB")) {
                Float op1 = stack.pop();
                Float op2 = stack.pop();

                stack.push(op2 - op1);
            } else if (instructionOp.equals("PRINT")) {
                System.out.println(stack.pop());
            } else {
                throw new Exception("Instruction " + instructionOp + " not implemented!");
            }
        }
    }
}
