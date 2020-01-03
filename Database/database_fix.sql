-- phpMyAdmin SQL Dump
-- version 4.9.1deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 11, 2019 at 04:37 AM
-- Server version: 10.3.15-MariaDB-1
-- PHP Version: 7.3.10-1+b1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gaspol_tubes`
--

-- --------------------------------------------------------

--
-- Table structure for table `dosen`
--

CREATE TABLE `dosen` (
  `id` int(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `id_keahlian1` int(10) NOT NULL,
  `id_keahlian2` int(11) NOT NULL,
  `kode_dosen` char(3) NOT NULL,
  `nip` char(10) NOT NULL,
  `jatah_sidang` int(5) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dosen`
--

INSERT INTO `dosen` (`id`, `nama`, `email`, `id_keahlian1`, `id_keahlian2`, `kode_dosen`, `nip`, `jatah_sidang`) VALUES
(1, 'Yudha Primadiansyah', 'yudhaprimadiansyah@gmail.com', 1, 2, 'YDA', '0000000001', 1),
(2, 'Dody Rifki Suraya', 'dodyrifki@gmail.com', 2, 1, 'DRS', '0000000002', 7),
(3, 'Nanda Putri Milania', 'nandaputri@gmail.com', 2, 1, 'NPM', '0000000004', 6),
(4, 'Muhammad Rafli Naufal', 'muhammadraflinaufal@gmail.com', 1, 2, 'MRF', '0000000200', 4),
(5, 'Asep Sunandar', 'asepsunan@gmail.com', 4, 2, 'ASR', '00000001', 0);

-- --------------------------------------------------------

--
-- Table structure for table `jadwal_mengajar_dosen`
--

CREATE TABLE `jadwal_mengajar_dosen` (
  `id` int(10) NOT NULL,
  `jam_mulai` time NOT NULL,
  `jam_selesai` time NOT NULL,
  `hari_ke` char(1) NOT NULL,
  `kode_dosen` char(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jadwal_mengajar_dosen`
--

INSERT INTO `jadwal_mengajar_dosen` (`id`, `jam_mulai`, `jam_selesai`, `hari_ke`, `kode_dosen`) VALUES
(1, '14:00:33', '14:00:33', '6', 'YDA'),
(2, '14:09:55', '14:09:55', '4', 'NPM'),
(3, '08:30:00', '10:30:00', '1', 'NPM'),
(4, '14:30:00', '16:30:00', '1', 'ASR');

-- --------------------------------------------------------

--
-- Table structure for table `jadwal_sidang`
--

CREATE TABLE `jadwal_sidang` (
  `id` int(10) NOT NULL,
  `jam_mulai` time NOT NULL,
  `jam_selesai` time NOT NULL,
  `tanggal` date NOT NULL,
  `kode_penguji1` char(3) NOT NULL,
  `kode_penguji2` char(3) NOT NULL,
  `id_pa` int(10) NOT NULL,
  `id_periode` int(11) NOT NULL,
  `id_ruangan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jadwal_sidang`
--

INSERT INTO `jadwal_sidang` (`id`, `jam_mulai`, `jam_selesai`, `tanggal`, `kode_penguji1`, `kode_penguji2`, `id_pa`, `id_periode`, `id_ruangan`) VALUES
(28, '11:00:00', '13:00:00', '2019-07-01', 'MRF', 'NPM', 91, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `keahlian`
--

CREATE TABLE `keahlian` (
  `id` int(10) NOT NULL,
  `nama_keahlian` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `keahlian`
--

INSERT INTO `keahlian` (`id`, `nama_keahlian`) VALUES
(1, 'Information Security'),
(2, 'Android Development'),
(3, 'Networking'),
(4, 'Digital Forensic'),
(5, 'WEB'),
(6, 'Image Processing');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nama` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nim` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`nama`, `email`, `nim`) VALUES
('Yudha Primadiansyah', 'yudhaprimadiansyah@gmail.com', '6706180083'),
('Atep Suratep', 'atep@mamail.com', '6706180199');

-- --------------------------------------------------------

--
-- Table structure for table `pengelola`
--

CREATE TABLE `pengelola` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `nip` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengelola`
--

INSERT INTO `pengelola` (`id`, `username`, `password`, `nama`, `nip`) VALUES
(1, 'admin', '664819d8c5343676c9225b5ed00a5cdc6f3a1ff3', 'Admin Pengelola', '081111');

-- --------------------------------------------------------

--
-- Table structure for table `periode_sidang`
--

CREATE TABLE `periode_sidang` (
  `id_periode` int(11) NOT NULL,
  `tahun` int(4) NOT NULL,
  `tanggal_awal` date DEFAULT NULL,
  `tanggal_akhir` date DEFAULT NULL,
  `periode_ke` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `periode_sidang`
--

INSERT INTO `periode_sidang` (`id_periode`, `tahun`, `tanggal_awal`, `tanggal_akhir`, `periode_ke`) VALUES
(1, 2019, '2019-11-15', '2019-11-30', 2),
(2, 2019, '2019-07-01', '2019-07-13', 1),
(3, 2019, '2019-12-01', '2019-12-17', 1);

-- --------------------------------------------------------

--
-- Table structure for table `proyek_akhir`
--

CREATE TABLE `proyek_akhir` (
  `id` int(11) NOT NULL,
  `judul` varchar(100) NOT NULL,
  `kode_dosbing1` char(3) NOT NULL,
  `kode_dosbing2` char(3) NOT NULL,
  `id_keahlian` int(10) NOT NULL,
  `nim` varchar(50) NOT NULL,
  `status` int(10) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proyek_akhir`
--

INSERT INTO `proyek_akhir` (`id`, `judul`, `kode_dosbing1`, `kode_dosbing2`, `id_keahlian`, `nim`, `status`) VALUES
(91, 'Aplikasi Penjadwalan', 'YDA', 'DRS', 2, '6706180083', 1),
(93, 'Aplikasi Pembibitan Berbasis Android', 'YDA', 'DRS', 2, '6706180199,6706180083', 1);

-- --------------------------------------------------------

--
-- Table structure for table `ruangan`
--

CREATE TABLE `ruangan` (
  `id` int(10) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ruangan`
--

INSERT INTO `ruangan` (`id`, `nama`) VALUES
(1, 'C2'),
(3, 'C1'),
(5, 'A4'),
(6, 'C2'),
(7, 'G4'),
(8, 'C9');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kode_dosen` (`kode_dosen`);

--
-- Indexes for table `jadwal_mengajar_dosen`
--
ALTER TABLE `jadwal_mengajar_dosen`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jadwal_sidang`
--
ALTER TABLE `jadwal_sidang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `keahlian`
--
ALTER TABLE `keahlian`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `pengelola`
--
ALTER TABLE `pengelola`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `periode_sidang`
--
ALTER TABLE `periode_sidang`
  ADD PRIMARY KEY (`id_periode`);

--
-- Indexes for table `proyek_akhir`
--
ALTER TABLE `proyek_akhir`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nim` (`nim`);

--
-- Indexes for table `ruangan`
--
ALTER TABLE `ruangan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dosen`
--
ALTER TABLE `dosen`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `jadwal_mengajar_dosen`
--
ALTER TABLE `jadwal_mengajar_dosen`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `jadwal_sidang`
--
ALTER TABLE `jadwal_sidang`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `keahlian`
--
ALTER TABLE `keahlian`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pengelola`
--
ALTER TABLE `pengelola`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `periode_sidang`
--
ALTER TABLE `periode_sidang`
  MODIFY `id_periode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `proyek_akhir`
--
ALTER TABLE `proyek_akhir`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94;

--
-- AUTO_INCREMENT for table `ruangan`
--
ALTER TABLE `ruangan`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
