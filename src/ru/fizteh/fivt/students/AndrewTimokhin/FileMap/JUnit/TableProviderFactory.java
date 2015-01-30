package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

public interface TableProviderFactory {

    /**
     * ���������� ������ ��� ������ � ����� ������.
     *
     * @param dir
     *            ���������� � ������� ���� ������.
     * @return ������ ��� ������ � ����� ������.
     * @throws IllegalArgumentException
     *             ���� �������� ���������� null ��� ����� ������������
     *             ��������.
     */
    TableProvider create(String dir);
}
