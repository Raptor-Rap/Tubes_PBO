package adminposyandu;

import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

public final class View extends javax.swing.JFrame {
    
    private void kosongkan_form(){
        txtNIK.setEditable(true);
        txtNIK.setText(null);
        txtNama.setText(null);
        txtAlamat.setText(null);
        txtTelepon.setText(null);
    }
    
    private void kosongkan_formSt(){
        txtNikSt.setEditable(true);
        txtNikSt.setText(null);
        txtNamaSt.setText(null);
        txtAlamatSt.setText(null);
        txtTelpSt.setText(null);
        cbJabatanSt.setSelectedItem(this);
    }
    
    private void kosongkan_Obat(){
        txtKode.setEditable(true);
        txtKode.setText(null);
        txtNamaObat.setText(null);
        cbJenis.setSelectedItem(this);
        txtStok.setText(null);
        txtHarga.setText(null);
        txtTgl.setDate(null);   
    }
    
    private void kosongkan_Jadwal(){
        txtDesa.setEditable(true);
        txtDesa.setText(null);
        txtPos.setText(null);
        txtLok.setText(null);
        pilihTgl.setDate(null);
        txtKetua.setText(null);
        txtKet.setText(null);
        txtKontak.setText(null);   
    }
    
    private void tampilkan_data(){
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("NIK");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("No. Telepon");
        
        
        try{
            String cari = txtCari.getText();
            int no = 1;
            String sql = "SELECT * FROM databayi WHERE" +
                         " NIK LIKE '%" + cari + "%'" +
                         " OR Nama LIKE '%" + cari + "%'" +
                         " OR Alamat LIKE '%" + cari + "%'" +
                         " OR No_Telp LIKE '%" + cari + "%'";

            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4)});  
            }
            tableDataBayi.setModel(model);
            
        }catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }
    
    private void tampilkan_dataSt(){
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("NIK");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("No. Telepon");
        model.addColumn("Jabatan");
        
        try{
            int no = 1;
            String sql = "SELECT * FROM datastaff";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)});
                
            }
            tableDataStaff.setModel(model);
            
        }catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }
    
    private void tampilObat(){
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Obat");
        model.addColumn("Nama Obat");
        model.addColumn("Jenis Obat");
        model.addColumn("Jumlah Stok");
        model.addColumn("Harga");
        model.addColumn("Exp");
        
        try{
            String cari = txtCari.getText();
            String sql = "SELECT * FROM stokobat WHERE" +
                         " Kode LIKE '%" + cari + "%'" +
                         " OR Nama LIKE '%" + cari + "%'" +
                         " OR Jenis LIKE '%" + cari + "%'" +
                         " OR Stok LIKE '%" + cari + "%'" +
                         " OR Harga LIKE '%" + cari + "%'";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7)});
                
            }
            TableObat.setModel(model);
            
        }catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }
    
    public static Date getTanggalFromTable(JTable table, int kolom){
        JTable tabel = table;
        String str_tgl = String.valueOf(tabel.getValueAt(tabel.getSelectedRow(),kolom));
        Date tanggal = null;
        try{
            tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(str_tgl);
        }catch(ParseException ex){
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tanggal;
    }
    
    private void tampilJadwal(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("Desa");
        model.addColumn("Nama Posyandu");
        model.addColumn("Lokasi");
        model.addColumn("Tanggal Pelaksanaan");
        model.addColumn("Nama Ketua");
        model.addColumn("Keterangan");
        model.addColumn("Kontak Person");
        
        try{
            int no = 1;
            String sql = "SELECT * FROM jadwal";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7)});
                
            }
            tableJadwal.setModel(model);
            
        }catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
        }
    }

    /**
     * Creates new form View
     */
    public View() {
        initComponents();
        tampilkan_data();
        kosongkan_form();
        tampilkan_dataSt();
        kosongkan_formSt();
        tampilObat();
        kosongkan_Obat();
        tampilJadwal();
        kosongkan_Jadwal();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelKiri = new javax.swing.JPanel();
        btnInputData = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JToggleButton();
        btnTransaksi = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnAbout = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnInputDataStaff = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btnObat = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        panelKanan = new javax.swing.JPanel();
        panelViewInputDataBayi = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNIK = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtTelepon = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDataBayi = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        panelViewInputDataStaff = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtNikSt = new javax.swing.JTextField();
        txtAlamatSt = new javax.swing.JTextField();
        txtNamaSt = new javax.swing.JTextField();
        txtTelpSt = new javax.swing.JTextField();
        cbJabatanSt = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDataStaff = new javax.swing.JTable();
        btnSimpanSt = new javax.swing.JButton();
        btnEditSt = new javax.swing.JButton();
        btnHapusSt = new javax.swing.JButton();
        btnBatalSt = new javax.swing.JButton();
        panelViewObat = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtNamaObat = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        btnHapusObat = new javax.swing.JButton();
        btnEditObat = new javax.swing.JButton();
        btnBatalObat = new javax.swing.JButton();
        txtCariObat = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        btnSimpanObat = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableObat = new javax.swing.JTable();
        txtTgl = new com.toedter.calendar.JDateChooser();
        cbJenis = new javax.swing.JComboBox<>();
        panelViewJadwal = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtDesa = new javax.swing.JTextField();
        txtPos = new javax.swing.JTextField();
        txtLok = new javax.swing.JTextField();
        txtKetua = new javax.swing.JTextField();
        btnJadwal = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        pilihTgl = new com.toedter.calendar.JDateChooser();
        txtKet = new javax.swing.JTextField();
        txtKontak = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableJadwal = new javax.swing.JTable();
        btnSimpanJK = new javax.swing.JButton();
        btnEditJK = new javax.swing.JButton();
        btnHapusJK = new javax.swing.JButton();
        btnBatalJK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("APLIKASI POSYANDU");
        setMinimumSize(new java.awt.Dimension(810, 510));
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelKiri.setBackground(new java.awt.Color(67, 154, 151));

        btnInputData.setBackground(new java.awt.Color(127, 140, 141));
        btnInputData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInputDataMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInputDataMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInputDataMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnInputDataMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnInputDataMouseReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icons8-document-40.png"))); // NOI18N
        jLabel2.setText("INPUT DATA BAYI");

        javax.swing.GroupLayout btnInputDataLayout = new javax.swing.GroupLayout(btnInputData);
        btnInputData.setLayout(btnInputDataLayout);
        btnInputDataLayout.setHorizontalGroup(
            btnInputDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnInputDataLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnInputDataLayout.setVerticalGroup(
            btnInputDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnInputDataLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        btnLogOut.setText("Log Out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        btnTransaksi.setBackground(new java.awt.Color(127, 140, 141));
        btnTransaksi.setPreferredSize(new java.awt.Dimension(260, 62));
        btnTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTransaksiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTransaksiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTransaksiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTransaksiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnTransaksiMouseReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icons8-clock-40.png"))); // NOI18N
        jLabel4.setText("INPUT JADWAL KEGIATAN");

        javax.swing.GroupLayout btnTransaksiLayout = new javax.swing.GroupLayout(btnTransaksi);
        btnTransaksi.setLayout(btnTransaksiLayout);
        btnTransaksiLayout.setHorizontalGroup(
            btnTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTransaksiLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel4)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        btnTransaksiLayout.setVerticalGroup(
            btnTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnTransaksiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icons8-user-male-40.png"))); // NOI18N
        jLabel5.setText("ADMIN POSYANDU");

        btnAbout.setBackground(new java.awt.Color(127, 140, 141));
        btnAbout.setPreferredSize(new java.awt.Dimension(260, 62));
        btnAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAboutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAboutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAboutMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAboutMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAboutMouseReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icons8-about-40.png"))); // NOI18N
        jLabel13.setText("ABOUT");

        javax.swing.GroupLayout btnAboutLayout = new javax.swing.GroupLayout(btnAbout);
        btnAbout.setLayout(btnAboutLayout);
        btnAboutLayout.setHorizontalGroup(
            btnAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAboutLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnAboutLayout.setVerticalGroup(
            btnAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAboutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnInputDataStaff.setBackground(new java.awt.Color(127, 140, 141));
        btnInputDataStaff.setPreferredSize(new java.awt.Dimension(260, 62));
        btnInputDataStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInputDataStaffMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInputDataStaffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInputDataStaffMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnInputDataStaffMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnInputDataStaffMouseReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icons8-news-40.png"))); // NOI18N
        jLabel14.setText("INPUT DATA STAFF");

        javax.swing.GroupLayout btnInputDataStaffLayout = new javax.swing.GroupLayout(btnInputDataStaff);
        btnInputDataStaff.setLayout(btnInputDataStaffLayout);
        btnInputDataStaffLayout.setHorizontalGroup(
            btnInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnInputDataStaffLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnInputDataStaffLayout.setVerticalGroup(
            btnInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnInputDataStaffLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap())
        );

        btnObat.setBackground(new java.awt.Color(127, 140, 141));
        btnObat.setPreferredSize(new java.awt.Dimension(260, 62));
        btnObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnObatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnObatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnObatMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnObatMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnObatMouseReleased(evt);
            }
        });

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icons8-plus-40.png"))); // NOI18N
        jLabel23.setText("INPUT STOK OBAT");

        javax.swing.GroupLayout btnObatLayout = new javax.swing.GroupLayout(btnObat);
        btnObat.setLayout(btnObatLayout);
        btnObatLayout.setHorizontalGroup(
            btnObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnObatLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnObatLayout.setVerticalGroup(
            btnObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelKiriLayout = new javax.swing.GroupLayout(panelKiri);
        panelKiri.setLayout(panelKiriLayout);
        panelKiriLayout.setHorizontalGroup(
            panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnInputData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnInputDataStaff, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
            .addComponent(btnObat, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
            .addComponent(btnTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
            .addComponent(btnAbout, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
            .addGroup(panelKiriLayout.createSequentialGroup()
                .addGroup(panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKiriLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(btnLogOut))
                    .addGroup(panelKiriLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelKiriLayout.setVerticalGroup(
            panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKiriLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addGap(27, 27, 27)
                .addComponent(btnInputData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btnInputDataStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(btnLogOut))
        );

        jPanel1.add(panelKiri, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 510));

        panelKanan.setBackground(new java.awt.Color(44, 62, 80));
        panelKanan.setLayout(new java.awt.CardLayout());

        panelViewInputDataBayi.setBackground(new java.awt.Color(98, 182, 183));
        panelViewInputDataBayi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(149, 165, 166));
        jPanel5.setPreferredSize(new java.awt.Dimension(520, 510));

        jLabel1.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jLabel1.setText("INPUT DATA BAYI");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 140, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(0, 140, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 37, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(0, 37, Short.MAX_VALUE))
        );

        panelViewInputDataBayi.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 520, 100));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("NIK");
        panelViewInputDataBayi.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama");
        panelViewInputDataBayi.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Alamat");
        panelViewInputDataBayi.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("No. Telepon");
        panelViewInputDataBayi.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        txtNIK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNIKActionPerformed(evt);
            }
        });
        panelViewInputDataBayi.add(txtNIK, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 258, -1));
        panelViewInputDataBayi.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 258, -1));

        txtAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlamatActionPerformed(evt);
            }
        });
        panelViewInputDataBayi.add(txtAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 258, -1));
        panelViewInputDataBayi.add(txtTelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 258, -1));

        tableDataBayi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableDataBayi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDataBayiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDataBayi);

        panelViewInputDataBayi.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 388, -1, 111));

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        panelViewInputDataBayi.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 80, -1));

        btnEdit.setText("Edit");
        btnEdit.setPreferredSize(new java.awt.Dimension(67, 23));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        panelViewInputDataBayi.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 350, -1, -1));

        btnHapus.setText("Hapus");
        btnHapus.setMaximumSize(new java.awt.Dimension(67, 23));
        btnHapus.setMinimumSize(new java.awt.Dimension(67, 23));
        btnHapus.setPreferredSize(new java.awt.Dimension(67, 23));
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        panelViewInputDataBayi.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 70, -1));

        btnBatal.setText("Batal");
        btnBatal.setPreferredSize(new java.awt.Dimension(67, 23));
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        panelViewInputDataBayi.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, -1, -1));

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        panelViewInputDataBayi.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, 100, 20));

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Cari");
        panelViewInputDataBayi.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, -1, -1));

        panelKanan.add(panelViewInputDataBayi, "card2");

        panelViewInputDataStaff.setBackground(new java.awt.Color(98, 182, 183));

        jPanel7.setBackground(new java.awt.Color(149, 165, 166));

        jLabel15.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jLabel15.setText("INPUT DATA STAFF");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 37, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addGap(0, 37, Short.MAX_VALUE)))
        );

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("NIK");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Nama");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Alamat");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("No. Telepon");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Jabatan");

        txtNikSt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNikStActionPerformed(evt);
            }
        });

        cbJabatanSt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ketua", "Wakil Ketua", "Medis", "Tim Informasi Posyandu" }));
        cbJabatanSt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbJabatanStActionPerformed(evt);
            }
        });

        tableDataStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableDataStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDataStaffMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableDataStaff);

        btnSimpanSt.setText("Simpan");
        btnSimpanSt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanStActionPerformed(evt);
            }
        });

        btnEditSt.setText("Edit");
        btnEditSt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditStActionPerformed(evt);
            }
        });

        btnHapusSt.setText("Hapus");
        btnHapusSt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusStActionPerformed(evt);
            }
        });

        btnBatalSt.setText("Batal");
        btnBatalSt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalStActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelViewInputDataStaffLayout = new javax.swing.GroupLayout(panelViewInputDataStaff);
        panelViewInputDataStaff.setLayout(panelViewInputDataStaffLayout);
        panelViewInputDataStaffLayout.setHorizontalGroup(
            panelViewInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelViewInputDataStaffLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelViewInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewInputDataStaffLayout.createSequentialGroup()
                        .addComponent(btnSimpanSt)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatalSt)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapusSt)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditSt))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelViewInputDataStaffLayout.createSequentialGroup()
                        .addGroup(panelViewInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(43, 43, 43)
                        .addGroup(panelViewInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNikSt)
                            .addComponent(txtNamaSt)
                            .addComponent(txtAlamatSt, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelViewInputDataStaffLayout.createSequentialGroup()
                        .addGroup(panelViewInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(panelViewInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbJabatanSt, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelpSt, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelViewInputDataStaffLayout.setVerticalGroup(
            panelViewInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewInputDataStaffLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panelViewInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtNikSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelViewInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtNamaSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelViewInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtAlamatSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelViewInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtTelpSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelViewInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cbJabatanSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(panelViewInputDataStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditSt)
                    .addComponent(btnHapusSt)
                    .addComponent(btnBatalSt)
                    .addComponent(btnSimpanSt))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelKanan.add(panelViewInputDataStaff, "card4");

        panelViewObat.setBackground(new java.awt.Color(98, 182, 183));
        panelViewObat.setForeground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(149, 165, 166));

        jLabel24.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jLabel24.setText("INPUT STOK OBAT");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel24)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 37, Short.MAX_VALUE)
                    .addComponent(jLabel24)
                    .addGap(0, 37, Short.MAX_VALUE)))
        );

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Jenis Obat");

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Kode Obat");

        txtKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeActionPerformed(evt);
            }
        });

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Nama Obat");

        txtNamaObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaObatActionPerformed(evt);
            }
        });

        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Jumlah Stok");

        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Harga");

        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Expired");

        btnHapusObat.setText("Hapus");
        btnHapusObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusObatActionPerformed(evt);
            }
        });

        btnEditObat.setText("Edit");
        btnEditObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditObatActionPerformed(evt);
            }
        });

        btnBatalObat.setText("Batal");
        btnBatalObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalObatActionPerformed(evt);
            }
        });

        txtCariObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariObatActionPerformed(evt);
            }
        });

        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Cari");

        btnSimpanObat.setText("Simpan");
        btnSimpanObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanObatActionPerformed(evt);
            }
        });

        TableObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        TableObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableObatMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TableObat);

        txtTgl.setDateFormatString("yyyy-MM-dd");

        cbJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Serbuk", "Tablet", "Pil", "Kapsul", "Larutan", "Salep", "Tetes" }));

        javax.swing.GroupLayout panelViewObatLayout = new javax.swing.GroupLayout(panelViewObat);
        panelViewObat.setLayout(panelViewObatLayout);
        panelViewObatLayout.setHorizontalGroup(
            panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelViewObatLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewObatLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelViewObatLayout.createSequentialGroup()
                        .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel26)
                            .addGroup(panelViewObatLayout.createSequentialGroup()
                                .addComponent(btnSimpanObat)
                                .addGap(18, 18, 18)
                                .addComponent(btnBatalObat, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnHapusObat)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditObat, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(txtCariObat, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewObatLayout.createSequentialGroup()
                        .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNamaObat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(txtKode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(cbJenis, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel30)
                                .addComponent(txtHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(jLabel31)
                                .addComponent(txtTgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60))))
        );
        panelViewObatLayout.setVerticalGroup(
            panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewObatLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelViewObatLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamaObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelViewObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpanObat)
                    .addComponent(btnBatalObat)
                    .addComponent(btnHapusObat)
                    .addComponent(btnEditObat))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(306, 306, 306))
        );

        panelKanan.add(panelViewObat, "card5");

        panelViewJadwal.setBackground(new java.awt.Color(98, 182, 183));

        jPanel6.setBackground(new java.awt.Color(149, 165, 166));

        jLabel3.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jLabel3.setText("INPUT JADWAL KEGIATAN");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 37, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(0, 37, Short.MAX_VALUE)))
        );

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nama Ketua");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Keterangan");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Kontak Person");

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Desa");

        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Nama Posyandu");

        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Lokasi");

        txtKetua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKetuaActionPerformed(evt);
            }
        });

        btnJadwal.setText("Lihat Jadwal");
        btnJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnJadwalMouseClicked(evt);
            }
        });
        btnJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJadwalActionPerformed(evt);
            }
        });

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Tanggal Pelaksanaan");

        pilihTgl.setDateFormatString("yyyy-MM-dd");

        txtKet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKetActionPerformed(evt);
            }
        });

        txtKontak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKontakActionPerformed(evt);
            }
        });

        tableJadwal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        tableJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableJadwalMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableJadwal);

        btnSimpanJK.setText("Simpan");
        btnSimpanJK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanJKActionPerformed(evt);
            }
        });

        btnEditJK.setText("Edit");
        btnEditJK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditJKActionPerformed(evt);
            }
        });

        btnHapusJK.setText("Hapus");
        btnHapusJK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusJKActionPerformed(evt);
            }
        });

        btnBatalJK.setText("Batal");
        btnBatalJK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalJKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelViewJadwalLayout = new javax.swing.GroupLayout(panelViewJadwal);
        panelViewJadwal.setLayout(panelViewJadwalLayout);
        panelViewJadwalLayout.setHorizontalGroup(
            panelViewJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelViewJadwalLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(panelViewJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21)
                    .addComponent(jLabel34)
                    .addComponent(jLabel36)
                    .addComponent(jLabel35)
                    .addComponent(txtDesa)
                    .addComponent(txtPos)
                    .addComponent(txtLok)
                    .addComponent(pilihTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelViewJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewJadwalLayout.createSequentialGroup()
                            .addComponent(txtKetua, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(48, 48, 48))
                        .addGroup(panelViewJadwalLayout.createSequentialGroup()
                            .addGroup(panelViewJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(txtKet, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(txtKontak, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewJadwalLayout.createSequentialGroup()
                        .addComponent(btnJadwal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))))
            .addGroup(panelViewJadwalLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelViewJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewJadwalLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnSimpanJK)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatalJK)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapusJK)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditJK))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 35, Short.MAX_VALUE))
        );
        panelViewJadwalLayout.setVerticalGroup(
            panelViewJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewJadwalLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelViewJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewJadwalLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKetua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelViewJadwalLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelViewJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewJadwalLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelViewJadwalLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelViewJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewJadwalLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKontak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelViewJadwalLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelViewJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnJadwal)
                    .addComponent(pilihTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(panelViewJadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanJK)
                    .addComponent(btnEditJK)
                    .addComponent(btnHapusJK)
                    .addComponent(btnBatalJK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelKanan.add(panelViewJadwal, "card3");

        jPanel1.add(panelKanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 520, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        LogIn login = new LogIn();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnInputDataMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInputDataMouseEntered
        btnInputData.setBackground(new Color(149, 165, 166));
    }//GEN-LAST:event_btnInputDataMouseEntered

    private void btnInputDataMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInputDataMouseExited
        btnInputData.setBackground(new Color(127, 140, 141));
    }//GEN-LAST:event_btnInputDataMouseExited

    private void btnInputDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInputDataMouseClicked
        // remove panel
        
        panelKanan.removeAll();
        panelKanan.repaint();
        panelKanan.revalidate();
        
        //add panel
        panelKanan.add(panelViewInputDataBayi);
        panelKanan.repaint();
        panelKanan.revalidate();
    }//GEN-LAST:event_btnInputDataMouseClicked

    private void btnInputDataMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInputDataMousePressed
        btnInputData.setBackground(new Color(189, 195, 199));
    }//GEN-LAST:event_btnInputDataMousePressed

    private void btnInputDataMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInputDataMouseReleased
        btnInputData.setBackground(new Color(149, 165, 166));
    }//GEN-LAST:event_btnInputDataMouseReleased

    private void btnTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransaksiMouseClicked
          // remove panel
        panelKanan.removeAll();
        panelKanan.repaint();
        panelKanan.revalidate();
        
        //add panel
        panelKanan.add(panelViewJadwal);
        panelKanan.repaint();
        panelKanan.revalidate();
    }//GEN-LAST:event_btnTransaksiMouseClicked

    private void btnTransaksiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransaksiMouseEntered
        btnTransaksi.setBackground(new Color(149, 165, 166));
    }//GEN-LAST:event_btnTransaksiMouseEntered

    private void btnTransaksiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransaksiMouseExited
        btnTransaksi.setBackground(new Color(127, 140, 141));
    }//GEN-LAST:event_btnTransaksiMouseExited

    private void btnTransaksiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransaksiMousePressed
         btnTransaksi.setBackground(new Color(189, 195, 199));
    }//GEN-LAST:event_btnTransaksiMousePressed

    private void btnTransaksiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransaksiMouseReleased
        btnTransaksi.setBackground(new Color(149, 165, 166));
    }//GEN-LAST:event_btnTransaksiMouseReleased

    private void txtAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlamatActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        try{
            String sql = "INSERT INTO databayi VALUES ('"+txtNIK.getText()+"','"+txtNama.getText()+"','"+txtAlamat.getText()+"','"+txtTelepon.getText()+"')";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Proses Simpan Data Berhasil.. ");
            tampilkan_data();
            kosongkan_form();
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tableDataBayiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDataBayiMouseClicked
        int baris = tableDataBayi.rowAtPoint(evt.getPoint());
        String nik = tableDataBayi.getValueAt(baris, 1).toString();
        txtNIK.setText(nik);
        
        String nama = tableDataBayi.getValueAt(baris, 2).toString();
        txtNama.setText(nama);
        
        String alamat = tableDataBayi.getValueAt(baris, 3).toString();
        txtAlamat.setText(alamat);
        
        String telp = tableDataBayi.getValueAt(baris, 4).toString();
        txtTelepon.setText(telp);
    }//GEN-LAST:event_tableDataBayiMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try{
            String sql = "UPDATE databayi SET nik='"+txtNIK.getText()+"',nama='"+txtNama.getText()+"',alamat='"+txtAlamat.getText()+"',no_telp='"+txtTelepon.getText()+"' WHERE nik = '"+txtNIK.getText()+"'";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Edit Data Berhasil..");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampilkan_data();
        kosongkan_form();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        try{
            String sql = "DELETE FROM databayi WHERE nik='"+txtNIK.getText()+"'";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus Data Berhasil..");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampilkan_data();
        kosongkan_form();
    }//GEN-LAST:event_btnHapusActionPerformed
 
    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        kosongkan_form();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseClicked
        About about = new About();
        about.setVisible(true);
    }//GEN-LAST:event_btnAboutMouseClicked

    private void btnAboutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseEntered
        btnAbout.setBackground(new Color(149, 165, 166));
    }//GEN-LAST:event_btnAboutMouseEntered

    private void btnAboutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseExited
        btnAbout.setBackground(new Color(127, 140, 141));
    }//GEN-LAST:event_btnAboutMouseExited

    private void btnAboutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMousePressed
        btnAbout.setBackground(new Color(189, 195, 199));
    }//GEN-LAST:event_btnAboutMousePressed

    private void btnAboutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseReleased
        btnAbout.setBackground(new Color(149, 165, 166));
    }//GEN-LAST:event_btnAboutMouseReleased

    private void txtNIKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNIKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNIKActionPerformed

    private void cbJabatanStActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbJabatanStActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbJabatanStActionPerformed

    private void btnInputDataStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInputDataStaffMouseClicked
        panelKanan.removeAll();
        panelKanan.repaint();
        panelKanan.revalidate();
        
        //add panel
        panelKanan.add(panelViewInputDataStaff);
        panelKanan.repaint();
        panelKanan.revalidate();
    }//GEN-LAST:event_btnInputDataStaffMouseClicked

    private void btnInputDataStaffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInputDataStaffMouseEntered
        btnInputDataStaff.setBackground(new Color(149, 165, 166));
    }//GEN-LAST:event_btnInputDataStaffMouseEntered

    private void btnInputDataStaffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInputDataStaffMouseExited
        btnInputDataStaff.setBackground(new Color(127, 140, 141));
    }//GEN-LAST:event_btnInputDataStaffMouseExited

    private void btnInputDataStaffMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInputDataStaffMousePressed
        btnInputDataStaff.setBackground(new Color(189, 195, 199));
    }//GEN-LAST:event_btnInputDataStaffMousePressed

    private void btnInputDataStaffMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInputDataStaffMouseReleased
        btnInputDataStaff.setBackground(new Color(149, 165, 166));
    }//GEN-LAST:event_btnInputDataStaffMouseReleased

    private void txtNikStActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNikStActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNikStActionPerformed

    private void btnSimpanStActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanStActionPerformed
        try{
            String sql = "INSERT INTO datastaff VALUES ('"+txtNikSt.getText()+"','"+txtNamaSt.getText()+"','"+txtAlamatSt.getText()+"','"+txtTelpSt.getText()+"','"+cbJabatanSt.getSelectedItem()+"')";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Proses Simpan Data Berhasil..");
            tampilkan_dataSt();
            kosongkan_formSt();
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSimpanStActionPerformed

    private void btnEditStActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditStActionPerformed
        try{
            String sql = "UPDATE datastaff SET nik='"+txtNikSt.getText()+"',nama='"+txtNamaSt.getText()+"',alamat='"+txtAlamatSt.getText()+"',no_telp='"+txtTelpSt.getText()+"',jabatan='"+cbJabatanSt.getSelectedItem()+"' WHERE nik = '"+txtNikSt.getText()+"'";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Edit Data Berhasil..");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampilkan_dataSt();
        kosongkan_formSt();
    }//GEN-LAST:event_btnEditStActionPerformed

    private void tableDataStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDataStaffMouseClicked
        int baris = tableDataStaff.rowAtPoint(evt.getPoint());
        String nik = tableDataStaff.getValueAt(baris, 1).toString();
        txtNikSt.setText(nik);
        
        String nama = tableDataStaff.getValueAt(baris, 2).toString();
        txtNamaSt.setText(nama);
        
        String alamat = tableDataStaff.getValueAt(baris, 3).toString();
        txtAlamatSt.setText(alamat);
        
        String telp = tableDataStaff.getValueAt(baris, 4).toString();
        txtTelpSt.setText(telp);
    }//GEN-LAST:event_tableDataStaffMouseClicked

    private void btnHapusStActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusStActionPerformed
        try{
            String sql = "DELETE FROM datastaff WHERE nik='"+txtNikSt.getText()+"'";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus Data Berhasil..");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampilkan_dataSt();
        kosongkan_formSt();
    }//GEN-LAST:event_btnHapusStActionPerformed

    private void btnBatalStActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalStActionPerformed
        kosongkan_formSt();
    }//GEN-LAST:event_btnBatalStActionPerformed

    private void btnObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnObatMouseClicked
        panelKanan.removeAll();
        panelKanan.repaint();
        panelKanan.revalidate();
        
        //add panel
        panelKanan.add(panelViewObat);
        panelKanan.repaint();
        panelKanan.revalidate();
    }//GEN-LAST:event_btnObatMouseClicked

    private void btnObatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnObatMouseEntered
        btnObat.setBackground(new Color(149, 165, 166));
    }//GEN-LAST:event_btnObatMouseEntered

    private void btnObatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnObatMouseExited
        btnObat.setBackground(new Color(127, 140, 141));
    }//GEN-LAST:event_btnObatMouseExited

    private void btnObatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnObatMousePressed
        btnObat.setBackground(new Color(189, 195, 199));
    }//GEN-LAST:event_btnObatMousePressed

    private void btnObatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnObatMouseReleased
        btnObat.setBackground(new Color(149, 165, 166));
    }//GEN-LAST:event_btnObatMouseReleased

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        tampilkan_data();
    }//GEN-LAST:event_txtCariActionPerformed

    private void btnEditObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditObatActionPerformed
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(txtTgl.getDate()));
        
        try{
            String sql = "UPDATE stokobat SET Kode='"+txtKode.getText()+"',Nama='"+txtNamaObat.getText()+"',Jenis='"+cbJenis.getSelectedItem()+"',Stok='"+txtStok.getText()+"',Harga='"+txtHarga.getText()+"',Tanggal='"+tanggal+"'";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Edit Data Berhasil..");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampilObat();
        kosongkan_Obat();
    }//GEN-LAST:event_btnEditObatActionPerformed

    private void btnHapusObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusObatActionPerformed
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(txtTgl.getDate()));
        
        try{
            String sql = "DELETE FROM stokobat WHERE Kode='"+txtKode.getText()+"'";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus Data Berhasil..");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampilObat();
        kosongkan_Obat();
    }//GEN-LAST:event_btnHapusObatActionPerformed

    private void txtNamaObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaObatActionPerformed

    private void txtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeActionPerformed

    private void btnSimpanObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanObatActionPerformed
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(txtTgl.getDate()));
        
        try{
            String sql = "INSERT INTO stokobat (Kode, Nama, Jenis, Stok, Harga, Tanggal) VALUES ('"+txtKode.getText()+"','"+txtNamaObat.getText()+"','"+cbJenis.getSelectedItem()+"','"+txtStok.getText()+"','"+txtHarga.getText()+"','"+tanggal+"')";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Proses Simpan Data Berhasil..");
            tampilObat();
            kosongkan_Obat();
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSimpanObatActionPerformed

    private void btnBatalObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalObatActionPerformed
        kosongkan_Obat();
    }//GEN-LAST:event_btnBatalObatActionPerformed

    private void txtCariObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariObatActionPerformed
        tampilObat();
    }//GEN-LAST:event_txtCariObatActionPerformed

    private void TableObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableObatMouseClicked
        int baris = TableObat.rowAtPoint(evt.getPoint());
        
        String kode = TableObat.getValueAt(baris, 0).toString();
        txtKode.setText(kode);
        
        String namaObat = TableObat.getValueAt(baris, 1).toString();
        txtNamaObat.setText(namaObat);
        
        String stok = TableObat.getValueAt(baris, 3).toString();
        txtStok.setText(stok);
        
        String harga = TableObat.getValueAt(baris, 4).toString();
        txtHarga.setText(harga);
        
        txtTgl.setDate(getTanggalFromTable(TableObat, 5));
    }//GEN-LAST:event_TableObatMouseClicked

    private void btnJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJadwalActionPerformed
        Jadwal jadwal = new Jadwal();
        jadwal.setVisible(true);
    }//GEN-LAST:event_btnJadwalActionPerformed

    private void btnJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJadwalMouseClicked

    }//GEN-LAST:event_btnJadwalMouseClicked

    private void txtKetuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKetuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKetuaActionPerformed

    private void txtKetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKetActionPerformed

    private void txtKontakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKontakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKontakActionPerformed

    private void btnSimpanJKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanJKActionPerformed
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(pilihTgl.getDate()));
        
        try{
            String sql = "INSERT INTO jadwal VALUES ('"+txtDesa.getText()+"','"+txtPos.getText()+"','"+txtLok.getText()+"','"+tanggal+"','"+txtKetua.getText()+"','"+txtKet.getText()+"','"+txtKontak.getText()+"')";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Proses Simpan Data Berhasil..");
            tampilJadwal();
            kosongkan_Jadwal();
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSimpanJKActionPerformed

    private void btnHapusJKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusJKActionPerformed
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(pilihTgl.getDate()));
        
        try{
            String sql = "DELETE FROM jadwal WHERE desa='"+txtDesa.getText()+"'";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus Data Berhasil..");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampilJadwal();
        kosongkan_Jadwal();
    }//GEN-LAST:event_btnHapusJKActionPerformed

    private void btnEditJKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditJKActionPerformed
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(pilihTgl.getDate()));
        
        try{
            String sql = "UPDATE jadwal SET desa='"+txtDesa.getText()+"',nama='"+txtPos.getText()+"',lokasi='"+txtLok.getText()+"',tanggal='"+tanggal+"',ketua='"+txtKetua.getText()+"',keterangan='"+txtKet.getText()+"',kontak='"+txtKontak.getText()+"' WHERE desa='"+txtDesa.getText()+"'";
            java.sql.Connection conn = (Connection)Konfig.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Edit Data Berhasil..");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampilJadwal();
        kosongkan_Jadwal();
    }//GEN-LAST:event_btnEditJKActionPerformed

    private void btnBatalJKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalJKActionPerformed
        kosongkan_Jadwal();
    }//GEN-LAST:event_btnBatalJKActionPerformed

    private void tableJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableJadwalMouseClicked
        int baris = tableJadwal.rowAtPoint(evt.getPoint());
        
        String desa = tableJadwal.getValueAt(baris, 1).toString();
        txtDesa.setText(desa);
        
        String nama = tableJadwal.getValueAt(baris, 2).toString();
        txtPos.setText(nama);
        
        String lok = tableJadwal.getValueAt(baris, 3).toString();
        txtLok.setText(lok);
        
        pilihTgl.setDate(getTanggalFromTable(tableJadwal, 4));
        
        String ketua = tableJadwal.getValueAt(baris, 5).toString();
        txtKetua.setText(ketua);
        
        String ket = tableJadwal.getValueAt(baris, 6).toString();
        txtKet.setText(ket);
        
        String kontak = tableJadwal.getValueAt(baris, 7).toString();
        txtKontak.setText(kontak);

    }//GEN-LAST:event_tableJadwalMouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new View().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableObat;
    private javax.swing.JPanel btnAbout;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBatalJK;
    private javax.swing.JButton btnBatalObat;
    private javax.swing.JButton btnBatalSt;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEditJK;
    private javax.swing.JButton btnEditObat;
    private javax.swing.JButton btnEditSt;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHapusJK;
    private javax.swing.JButton btnHapusObat;
    private javax.swing.JButton btnHapusSt;
    private javax.swing.JPanel btnInputData;
    private javax.swing.JPanel btnInputDataStaff;
    private javax.swing.JButton btnJadwal;
    private javax.swing.JToggleButton btnLogOut;
    private javax.swing.JPanel btnObat;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnSimpanJK;
    private javax.swing.JButton btnSimpanObat;
    private javax.swing.JButton btnSimpanSt;
    private javax.swing.JPanel btnTransaksi;
    private javax.swing.JComboBox<String> cbJabatanSt;
    private javax.swing.JComboBox<String> cbJenis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel panelKanan;
    private javax.swing.JPanel panelKiri;
    private javax.swing.JPanel panelViewInputDataBayi;
    private javax.swing.JPanel panelViewInputDataStaff;
    private javax.swing.JPanel panelViewJadwal;
    private javax.swing.JPanel panelViewObat;
    private com.toedter.calendar.JDateChooser pilihTgl;
    private javax.swing.JTable tableDataBayi;
    private javax.swing.JTable tableDataStaff;
    private javax.swing.JTable tableJadwal;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtAlamatSt;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtCariObat;
    private javax.swing.JTextField txtDesa;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtKet;
    private javax.swing.JTextField txtKetua;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtKontak;
    private javax.swing.JTextField txtLok;
    private javax.swing.JTextField txtNIK;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNamaObat;
    private javax.swing.JTextField txtNamaSt;
    private javax.swing.JTextField txtNikSt;
    private javax.swing.JTextField txtPos;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JTextField txtTelpSt;
    private com.toedter.calendar.JDateChooser txtTgl;
    // End of variables declaration//GEN-END:variables

}