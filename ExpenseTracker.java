
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ExpenseTracker extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField expenseField, descriptionField, dateField,IDField;
    private JButton addButton,removebutton;
    private JTextArea expenseList;
    private JPanel inputPanel, expenseListPanel;
    private JComboBox categories;
    private Expense[] expenses = new Expense [100];
    private int size=0;
    private double total;
    public ExpenseTracker() {
        super("Expense Tracker");
        
        // Set up the input panel
        inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("ID"));
        IDField = new JTextField(10);
        inputPanel.add(IDField);
      
        inputPanel.add(new JLabel("Expense: "));
        expenseField = new JTextField(10);
        inputPanel.add(expenseField);
        inputPanel.add(new JLabel("Description: "));
        descriptionField = new JTextField(10);
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("category: "));

        String[] optionsToChoose = {"Food","Housing","Transportation","Groceries","Entertainement","Health","Personal care","Education","Gifts","Donations","Insurance","Taxes","Reparations","other"};
        categories = new JComboBox<>(optionsToChoose);
        inputPanel.add(categories);
        
        inputPanel.add(new JLabel("Date (dd/mm/yyyy): "));
        dateField = new JTextField(10);
        inputPanel.add(dateField);
        removebutton=new JButton("remove");
        addButton = new JButton("Add Expense");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String expenseStr = expenseField.getText();
                String description = descriptionField.getText();
                String dateStr = dateField.getText();
                String idstr = IDField.getText();
                
                boolean isValid = !expenseStr.equals("") && !description.equals("") && !dateStr.equals("");
                String category = (String) categories.getSelectedItem();
                if (isValid && checkId(Integer.parseInt(idstr))) {
                	
                    
                        double expense = Double.parseDouble(expenseStr);
                        
                        try {
							addExpense(Integer.parseInt(idstr) , expense, description, dateStr,category);
						} catch (NumberFormatException | ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Invalid expense amount. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
						}
                        expenseField.setText("");
                        descriptionField.setText("");
                        //dateField.setText("");
                    /* catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid date format. Please use dd/mm/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid expense amount. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }*/
                }
            }
        });
        
     
        
        removebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane prompt = new JOptionPane ();
            	JOptionPane.showInputDialog(null,"input id");
            		removeExpense(prompt.getValue().toString());
            		
            		return;
            	
        }
    
        });
        inputPanel.add(removebutton);
        inputPanel.add(addButton);
       
        // Set up the expense list panel
        expenseListPanel = new JPanel(new BorderLayout());
        expenseList = new JTextArea(10, 30);
        expenseList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(expenseList);
        expenseListPanel.add(new JLabel("Expense List:"), BorderLayout.NORTH);
        expenseListPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Set up the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(expenseListPanel, BorderLayout.CENTER);
        
        // Add the main panel to the frame
        getContentPane().add(mainPanel);
        
        
        // Set up the frame
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void addExpense(int id,double p , String de , String d, String c) throws ParseException {
        expenses[size] = new Expense (id,p,de,d,c);
        size++;
        total +=p;
        updateExpenseList();
    }
    
    private boolean checkId (int id) {
    	for (int i=0; i<size ; i++) {
        	if (id == expenses[i].getId()) return false;
        }
    	return true;
    }
    
   /*@SuppressWarnings("unlikely-arg-type")
/*private void removeExpense(String id) {
        int i;
        for (i = 0; i < size; i++) {
            if (id.equals(expenses[i].getId())) {
                total = total-expenses[i].getPrice();
                updateExpenseList();
                for (int j = i; j < size - 1; j++) {
                    expenses[j] = expenses[j + 1];
                  
                } 
                break;
                
            }
            if (i == size) {
                JOptionPane.showMessageDialog(null, "ID NOT EXISTENT");
                return;
            }
        }
        
       */
   private void removeExpense(String id) {
	    boolean found = false;
	    int i;
	    for (i = 0; i < size; i++) {
	        if (id.equals(Integer.toString(expenses[i].getId()))) {
	            found = true;
	            total -= expenses[i].getPrice();
	            for (int j = i; j < size - 1; j++) {
	                expenses[j] = expenses[j + 1];
	            } 
	            JOptionPane.showMessageDialog(null, "nice deleteion done");
	            break;
	        }
	    }
	    if (found) {
	        JOptionPane.showMessageDialog(null, "ID NOT EXISTENT");
	        return;
	    }
	    
	

        
        
        size--;
        updateExpenseList();
    }
   

    
    private void updateExpenseList() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < size; i++) {
            sb.append(expenses[i].info());
         
        }
        sb.append("\nTotal: ").append(total);
        expenseList.setText(sb.toString());
    }
    
    public static void main(String[] args) {
        new ExpenseTracker();
    }
}