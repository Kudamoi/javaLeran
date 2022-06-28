import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JPanel mainPanel;
    private JButton submit;
    private JPanel management;
    private JButton reset;
    private JTextField name;
    private JTextField surName;
    private JTextField endName;
    private JLabel endLabel;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JPanel expandedFields;
    private JPanel collapsedFields;
    private JLabel collapsedLabel;
    private JTextArea collapsedFiled;

    public MainForm() {
        reset.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                expandedFields.setVisible(true);
                collapsedFields.setVisible(false);
                submit.setText("Обьеденить");
                collapsedFiled.setText("");
                name.setText("");
                surName.setText("");
                endName.setText("");
            }
        });
        submit.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (collapsedFields.isVisible()) {
                    expandedFields.setVisible(true);
                    collapsedFields.setVisible(false);
                    submit.setText("Обьеденить");
                } else {
                    String nameText = name.getText();
                    String surNameText = surName.getText();
                    String endNameText = endName.getText();

                    if (nameText.length() > 0 && surNameText.length() > 0) {
                        expandedFields.setVisible(false);
                        collapsedFields.setVisible(true);
                        collapsedFiled.setText(surNameText + " " + nameText + " " + endNameText);
                        submit.setText("Разьеденить");
                    } else {
                        JOptionPane.showMessageDialog(
                                mainPanel,
                                "Заполните обязательные поля имя и фамилия",
                                "Ошибка заполнения формы",
                                JOptionPane.PLAIN_MESSAGE
                        );
                    }
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
