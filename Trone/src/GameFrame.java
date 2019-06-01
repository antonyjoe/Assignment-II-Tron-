import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class GameFrame extends javax.swing.JFrame {
    private Board board;
    private Game game;
    public GameFrame(Board board, Game game) {
        initComponents();
        setTitle("Tron");

        this.board = board;
        this.game = game;
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(board, gridBagConstraints);

        pack();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        progressLabel = new javax.swing.JLabel();
        playButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        progressLabel.setText("Ready?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        getContentPane().add(progressLabel, gridBagConstraints);

        playButton.setText("Start");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        getContentPane().add(playButton, gridBagConstraints);

        pack();
    }

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                game.play();
            }
        });
    }

    public JButton getPlayButton()
    {
        return this.playButton;
    }

    public JLabel getProgressLabel()
    {
        return this.progressLabel;
    }

    private javax.swing.JButton playButton;
    private javax.swing.JLabel progressLabel;
}
