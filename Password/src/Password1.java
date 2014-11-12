import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.awt.event.*;

public class Password1 extends JPanel implements ActionListener {
    private static String Ok = "Ok";
    private static String HELP = "help";

    private JPasswordField password;
    private JFrame Frame;

    public Password1(JFrame Frame1) {
        Frame = Frame1;
        password = new JPasswordField(10);
        password.setActionCommand(Ok);
        password.addActionListener(this);
        JLabel label = new JLabel("Enter the password:");
        label.setLabelFor(password);
        JComponent button = createButtonPanel();
        JPanel pass = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        pass.add(label);
        pass.add(password);

        add(pass);
        add(button);
    }

    protected JComponent createButtonPanel() {
        JPanel mainbox = new JPanel(new GridLayout(0,1));
        JButton helpButton = new JButton("Help");
        JButton OkButton = new JButton("Ok");
        helpButton.setActionCommand(HELP);
        OkButton.setActionCommand(Ok);
        helpButton.addActionListener(this);
        OkButton.addActionListener(this);
        mainbox.add(OkButton);
        mainbox.add(helpButton);

        return mainbox;
    }

    public void actionPerformed(ActionEvent e) {
        String x = e.getActionCommand();

        if (Ok.equals(x)) {
            char[] passwordtyped = password.getPassword();
            if (isPasswordCorrect(passwordtyped)) {
                JOptionPane.showMessageDialog(Frame,
                    "Welcome you are logged in!");
            } else {
                JOptionPane.showMessageDialog(Frame,
                    "Sorry. Type in the password",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            }
            Arrays.fill(passwordtyped, '0');

            password.selectAll();
            resetFocus();
        } else { 
            JOptionPane.showMessageDialog(Frame,"You should remember your password next time =[");
        }
    }
    private static boolean isPasswordCorrect(char[] passwordtyped) {
        boolean a = true;
        char[] bugaboo = { 'b', 'u', 'g', 'a', 'b', 'o', 'o' };
        if (passwordtyped.length != bugaboo.length) {
            a = false;
        } else {
            a = Arrays.equals (passwordtyped, bugaboo);
        }
        Arrays.fill(bugaboo,'0');

        return (a);
    }
    protected void resetFocus() {
        password.requestFocusInWindow();
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("LOGIN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Password1 newContentPane = new Password1(frame);
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);
        frame.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                newContentPane.resetFocus();
            }
        }
        )
        ;
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
             SwingUtilities.invokeLater(new Runnable() {
            public void run() {

		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });
    }
}
